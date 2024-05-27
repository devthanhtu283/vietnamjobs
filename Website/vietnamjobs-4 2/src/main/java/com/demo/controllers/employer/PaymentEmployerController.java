package com.demo.controllers.employer;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.demo.dtos.AccountDTO;
import com.demo.dtos.TransactionHistoryDTO;
import com.demo.entities.Account;
import com.demo.entities.TransactionHistory;
import com.demo.services.AccountService;
import com.demo.services.TransactionHistoryService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping({"employer/payment" })
public class PaymentEmployerController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private TransactionHistoryService transactionHistoryService;
	@GetMapping({ "", "index", "/" })
	public String index(ModelMap map, Authentication auth) {

        Account account = accountService.getByUsername(auth.getName());
        map.put("wallet", account.getWallet());
        return "employer/payment/index";
	}
	@GetMapping("return")
	public String returnPayment(@RequestParam("vnp_Amount") String amount, @RequestParam("vnp_TransactionNo") int vnp_TransactionNo, @RequestParam("vnp_PayDate") String vnp_PayDate, ModelMap modelMap, Authentication authentication) {
	
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			double amount1 = Double.parseDouble(amount) / 100;
			modelMap.put("amount", amount1);
			modelMap.put("payDate", dateFormat2.format(dateFormat.parse(vnp_PayDate)));
			AccountDTO account = accountService.findByUsername(authentication.getName());
			account.setWallet(account.getWallet() + amount1);
			accountService.save(account);
			Account account2 = new Account();
			account2.setId(accountService.findByUsername(authentication.getName()).getId());
			TransactionHistoryDTO transactionHistory = new TransactionHistoryDTO();
			transactionHistory.setAccountid(accountService.findByUsername(authentication.getName()).getId());
			transactionHistory.setCreated(new Date());
			transactionHistory.setStatus(true);
			transactionHistory.setTotal(amount1);
			transactionHistory.setTradingCode(vnp_TransactionNo);
			transactionHistoryService.save(transactionHistory);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "employer/payment/return";
	}
	@PostMapping("payment")
	public RedirectView payment(@RequestParam("amount") String amount) throws Exception {
		  
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount1 = Long.parseLong(amount) * 100;
        
        
        String vnp_TxnRef = Config.getRandomNumber(8);

        String vnp_TmnCode = Config.vnp_TmnCode;
        
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount1));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");
        
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", "192.168.1.20");

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        java.util.Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
        System.out.println(paymentUrl);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(paymentUrl);
        return redirectView;
	}
	
	

}
