package com.demo.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.dtos.FollowDB;
import com.demo.entities.Category;
import com.demo.entities.Employer;
import com.demo.entities.TypeAccount;
import com.demo.services.AccountService;
import com.demo.services.AdminService;
import com.demo.services.EmployerService;
import com.demo.services.FollowService;
import com.demo.services.MailService;
import com.demo.services.SeekerService;
import com.demo.services.TypeAccountService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("notification")
public class NotificationController {
	@Autowired
	private FollowService followService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private SeekerService seekerService;
	@GetMapping({ "", "index", "/" })
	public String index(ModelMap modelmap, Authentication authentication, HttpSession httpSession) {	
		
		modelmap.put("follows", followService.listPostFollowBySeekerID(seekerService.findByAccountID(accountService.findByUsername(authentication.getName()).getId()).getId()));
		modelmap.put("seekerID", seekerService.findByAccountID(accountService.findByUsername(authentication.getName()).getId()).getId());
		return "user/notification/index";
	}
	
}
