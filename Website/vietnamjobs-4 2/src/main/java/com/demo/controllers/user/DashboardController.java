package com.demo.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dtos.PostingDTO;
import com.demo.dtos.PostingspaymentDTO;
import com.demo.helpers.CheckDateHelper;
import com.demo.services.AccountService;
import com.demo.services.CategoryService;
import com.demo.services.EmployerService;
import com.demo.services.FollowService;
import com.demo.services.PostingPaymentService;
import com.demo.services.PostingService;
import com.demo.services.SeekerService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping({ "", "home", "/" })
public class DashboardController {
	
	@Autowired
	private FollowService followService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private SeekerService seekerService;
	@Autowired
	private EmployerService employerService;
	
	@Autowired
	private PostingService postingService;
	
	@Autowired
	private PostingPaymentService paymentService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping({ "", "index", "/" })
	public String index(ModelMap modelMap , HttpSession httpSession,  Authentication authentication) {
		CheckDateHelper checkDateHelper = new CheckDateHelper();
		for (PostingDTO postingDTO : postingService.findAll()) {
			if(!checkDateHelper.updatePostingbyDate(postingDTO)) {
				postingDTO.setStatus(false);
				postingService.save(postingDTO);
			}
		}
		for (PostingspaymentDTO postingspaymentDTO : paymentService.findAll()) {
			if(!checkDateHelper.updatePostingPaymentbyDate(postingspaymentDTO)) {
				postingspaymentDTO.setStatus(false);
				paymentService.save(postingspaymentDTO);
			}
		}

		modelMap.put("employers", employerService.findAll2());
		modelMap.put("postings", postingService.findAllLimit(9));
		List<Object[]> counts = categoryService.getCountPostingsByCategory();
		modelMap.addAttribute("counts", counts);
		if(authentication!= null) {
			httpSession.setAttribute("seekerID", seekerService.findByAccountID(accountService.findByUsername(authentication.getName()).getId()).getId());
			httpSession.setAttribute("oldPostings", followService.listPostFollowBySeekerID(seekerService.findByAccountID(accountService.findByUsername(authentication.getName()).getId()).getId()));
		}
		return "user/dashboard/index";
	}
}
