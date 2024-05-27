package com.demo.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.dtos.AccountDTO;
import com.demo.dtos.EmployerDTO;
import com.demo.dtos.SeekerDTO;

import com.demo.services.AccountService;
import com.demo.services.EmployerService;
import com.demo.services.SeekerService;
import com.demo.servicesModelMap.ServiceModelMap;

@Controller
@RequestMapping({ "checkmail" })
public class CheckmailController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private SeekerService seekerService;
	@Autowired
	private EmployerService employerService;
	@Autowired
	private ServiceModelMap serviceModelMap;

	@GetMapping({ "/{username}/{securityCode}" })
	public String index(@PathVariable("username") String username, @PathVariable("securityCode") String securityCode,
			RedirectAttributes redirectAttributes) {
		AccountDTO accountDTO = accountService.findByUsername(username);
		System.out.println(accountDTO.getSecurityCode());
		System.out.println(securityCode);
		String s1 = accountDTO.getSecurityCode().toString();
		String s2 = securityCode.toString();
		if(s1.equals(s2)) {
			accountDTO.setStatus(true);
			if(accountService.save(accountDTO)) {
				String typeAccount = accountDTO.getTypeAccount().trim().toLowerCase();
				
				if(typeAccount.equalsIgnoreCase("ROLE_SEEKER")) {
					SeekerDTO seekerDTO =  new SeekerDTO();
					seekerDTO.setAccountID(accountDTO.getId());
					seekerDTO.setAccountName(accountDTO.getUsername());
					seekerDTO.setStatus(true);
					if(seekerService.save(seekerDTO)) {
						System.out.println("pass");
						return "account/index2";
					} else {
						System.out.println("failed");
						return "account/accessdenied";
					}
				} else {
					EmployerDTO employerDTO =  new EmployerDTO();
					employerDTO.setAccountId(accountDTO.getId());
					employerDTO.setAccountName(accountDTO.getUsername());
					employerDTO.setStatus(true);
					employerDTO.setAddress("your Address");
					employerDTO.setCover("company_cover_1.jpg");
					employerDTO.setDescription("Your Description");
					employerDTO.setFolow(0);
					employerDTO.setLink("yourlink");
					employerDTO.setLogo("vietnamjob.jpg");
					employerDTO.setName("Your Company");
					employerDTO.setScale("O người");
					employerDTO.setStatus(true);
					
					if(employerService.save(employerDTO)) {
						System.out.println("pass");
						return "account/index2";
					} else {
						System.out.println("failed");
						return "account/accessdenied";
					}
				}
							
			} else {
				return "account/accessdenied";
			}
		} else {
			System.out.println("failed");
			return "account/accessdenied";
		}
		
		

	}
	@GetMapping({ "forgotpassword/{username}/{sercuritycode}" })
	public String index2(@PathVariable("username") String username, @PathVariable("sercuritycode") String sercuritycode,
			ModelMap modelMap) {
		AccountDTO accountDTO = accountService.findByUsername(username);
		String s1 = accountDTO.getSecurityCode().trim().toString();
		String s2 = sercuritycode.toString();
		if(s1.equals(s2)) {
			modelMap.put("username", username);
			return "account/changepassword";
		} else {
			System.out.println("failed");
			return "account/accessdenied";
		}
	}



}
