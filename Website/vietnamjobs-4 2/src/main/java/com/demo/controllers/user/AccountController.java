package com.demo.controllers.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.dtos.AccountDTO;
import com.demo.entities.TypeAccount;
import com.demo.helpers.RandomHelper;
import com.demo.services.AccountService;
import com.demo.services.MailService;
import com.demo.servicesModelMap.ServiceModelMap;


@Controller
@RequestMapping({"account"})
public class AccountController {
	@Autowired
	private ServiceModelMap serviceModelMap;
	@Autowired
	private Environment environment;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private AccountService accountService;
	@Autowired
	private MailService mailService;


	@GetMapping({"register"})
	public String index(ModelMap modelMap) {
		AccountDTO accountDTO = new AccountDTO();
		modelMap.put("accountDTO", accountDTO);
		return "account/register";
	}
	@GetMapping({"forgotpassword"})
	public String fogotpassword() {
		return "account/forgotpassword";
	}
	@GetMapping({"changepassword"})
	public String changepassword() {
		return "account/changepassword";
	}
	@PostMapping({"changepassword"})
	public String changepassword(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("confirmpassword") String confirmpassword, RedirectAttributes redirectAttributes) {
		if(confirmpassword.trim().equals(password.trim())) {
			AccountDTO accountDTO = accountService.findByUsername(username);
			accountDTO.setPassword(encoder.encode(password.trim()));
			if(accountService.save(accountDTO)) {
				redirectAttributes.addFlashAttribute("successMk", "pass");
				return "redirect:/account/login";
			} else {
				redirectAttributes.addFlashAttribute("errorMk", "failed");
				return "redirect:/account/changepassword";
			}
		} else {
			redirectAttributes.addFlashAttribute("errorMk", "failed");
			return "redirect:/account/changepassword";
		}
		
	}
	
	@PostMapping({"forgotpassword"})
	public String forgotpassword(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
		
		RandomHelper random = new RandomHelper();
		String newpasscode = random.randomAlphaNumeric(20);
		AccountDTO accountDTO = accountService.findByEmail(email);
		accountDTO.setSecurityCode(newpasscode);
		if (accountService.save(accountDTO)) {
			String from =  environment.getProperty("spring.mail.username");
			String url = environment.getProperty("BASE_URL");
			String content = "<p>Xin chào "+ accountDTO.getUsername() +",\r\n</p>"
					+ "\r\n"
					+ "<p>Bạn đang đổi mật khẩu Tài khoản VietNamJobs. Để xác thực thông tin, vui lòng click vào đường dẫn sau.\r\n"
					+ "\r\n</p>"
					+ "<p><a href = '" + url +"checkmail/forgotpassword/" + accountDTO.getUsername() +"/"+ accountDTO.getSecurityCode() + "'><button> Xác thực ngay </button></a>\r\n</p>"
					+ "<p>Trân trọng,\r\n </p>"
					+ "<p>VietNamJobs</p>";
			if(mailService.sendMailAccuracy(from, accountDTO.getEmail(), content)) {
				redirectAttributes.addFlashAttribute("success", "Send Mail");
				return "redirect:/account/login";
			} else {
				redirectAttributes.addFlashAttribute("error", "Failed 2");
				return "redirect:/account/register";
			}		
		} else {
			redirectAttributes.addFlashAttribute("error", "Send Mail Fail");
			return "redirect:/account/forgotpassword";
		}
		
		
	}
	
	@PostMapping("register")
	public String register(@ModelAttribute AccountDTO accountDTO, @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
			RandomHelper random = new RandomHelper();
			String securityCode = random.randomAlphaNumeric(10);
			accountDTO.setCreated(new Date());
			accountDTO.setStatus(false);
			TypeAccount typeAccount = serviceModelMap.findbytypeaccountname(accountDTO.getTypeAccount());
			accountDTO.setTypeAccountID(typeAccount.getId());
			accountDTO.setPassword(encoder.encode(password.trim()));
			accountDTO.setSecurityCode(securityCode);
			accountDTO.setWallet(0);
			
			if (accountService.save(accountDTO)) {
				String from =  environment.getProperty("spring.mail.username");
				String url = environment.getProperty("BASE_URL");
				String content = "<p>Xin chào "+ accountDTO.getUsername() +",\r\n</p>"
						+ "\r\n"
						+ "<p>Cảm ơn bạn đã đăng ký tài khoản trên VietNamJobs. Để có được trải nghiệm dịch vụ và được hỗ trợ tốt nhất, bạn cần hoàn thiện xác thực tài khoản.\r\n"
						+ "\r\n</p>"
						+ "<p><a href = '" + url +"checkmail/" + accountDTO.getUsername() +"/"+ securityCode + "'><button> Xác thực ngay </button></a>\r\n</p>"
						+ "<p>Trân trọng,\r\n </p>"
						+ "<p>VietNamJobs</p>";
				if(mailService.sendMailAccuracy(from, accountDTO.getEmail(), content)) {
					redirectAttributes.addFlashAttribute("success", "Send Mail");
					return "redirect:/account/login";
				} else {
					redirectAttributes.addFlashAttribute("error", "Failed 2");
					return "redirect:/account/register";
				}			
			} else {
				redirectAttributes.addFlashAttribute("error", "Failed 3");
				return "redirect:/account/register";
			}
		}
		
		
	
	@GetMapping({"login"})
	public String login(ModelMap modelMap) {
		AccountDTO accountDTO = new AccountDTO();
		modelMap.put("accountDTO", accountDTO);
		return "account/login";
	}
	@GetMapping("accessdenied")
	public String accessdenied() {
		return "account/accessdenied";
	}
	

}
