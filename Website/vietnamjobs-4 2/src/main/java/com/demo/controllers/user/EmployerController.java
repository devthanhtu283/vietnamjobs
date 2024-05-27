package com.demo.controllers.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.dtos.EmployerDTO;
import com.demo.dtos.PostingDTO;
import com.demo.services.EmployerService;
import com.demo.services.FollowService;
import com.demo.services.PostingService;

@Controller
@RequestMapping("home/employer")
public class EmployerController {
	@Autowired
	private EmployerService employerService;
	
	@Autowired
	private PostingService postingService;
	
	@Autowired
	private FollowService followService;

	@GetMapping({ "", "index", "/" })
	public String index(ModelMap modelMap) {
		modelMap.put("employers", employerService.findAll());
		return "user/employer/index";
	}
	
	@GetMapping("{id}")
	public String details(ModelMap modelMap, @PathVariable("id") int id) {
		modelMap.put("company", employerService.findByID(id));
		modelMap.put("postings", postingService.findByEmployerId(id));
		modelMap.put("follows", followService.listPostFollowByEmployerID(id).size());
		return "user/employer/details";
	}
	@PostMapping("/search")
	public String search(ModelMap modelMap, @RequestParam("name") String name) {
		System.out.print(name);
		modelMap.put("employers", employerService.searchByName(name));
		return "user/employer/index";
	}
}
