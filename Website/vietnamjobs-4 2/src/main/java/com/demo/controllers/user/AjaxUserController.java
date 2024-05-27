package com.demo.controllers.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dtos.ApplicationHistoryDTO;
import com.demo.dtos.FollowDB;
import com.demo.services.AccountService;
import com.demo.services.FollowService;
import com.demo.services.MailService;
import com.demo.services.SeekerService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("ajaxUser")
public class AjaxUserController {
	@Autowired
	private FollowService followService;
	@Autowired
	private MailService mailService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private SeekerService seekerService;
	@GetMapping(value = "notification", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public Map<String, Object> notification(HttpSession httpSession, Authentication authentication) {
		Map<String, Object> maps = new HashMap<String, Object>();
		
		List<FollowDB> oldPostings = new ArrayList<FollowDB>();
		List<FollowDB> newPostings = new ArrayList<FollowDB>();
		if(authentication!= null) {
			oldPostings = (List<FollowDB>) httpSession.getAttribute("oldPostings");
			newPostings = followService.listPostFollowBySeekerID(seekerService.findByAccountID(accountService.findByUsername(authentication.getName()).getId()).getId());
		
			maps.put("count", newPostings.size() - oldPostings.size());
			maps.put("postings", newPostings);
		}
	
		maps.put("count", newPostings.size() - oldPostings.size());
		maps.put("postings", newPostings);
		
		return maps;
		
	}
}
