package com.demo.controllers.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.dtos.ApplicationHistoryDTO;
import com.demo.dtos.EmployerDTO;
import com.demo.dtos.PostingDTO;
import com.demo.dtos.PostingspaymentDTO;
import com.demo.dtos.SeekerDTO;
import com.demo.services.AccountService;
import com.demo.services.ApplicationHistoryService;
import com.demo.services.EmployerService;
import com.demo.services.FollowService;
import com.demo.services.PostingPaymentService;
import com.demo.services.PostingService;
import com.demo.services.SeekerService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("home/posting")
public class PostingController {

	@Autowired
	private PostingService postingService;
	@Autowired
	private EmployerService employerService;
	@Autowired
	private ApplicationHistoryService applicationHistoryService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private SeekerService seekerService;
	@Autowired
	private FollowService followService;

	@Autowired
	private PostingPaymentService postingPaymentService;

	@GetMapping({ "", "index", "/" })
	public String index(ModelMap modelMap) {
		List<PostingDTO> postingDTOVIPs = new ArrayList<PostingDTO>();
		List<PostingDTO> postingDTOs = postingService.findAll();
		List<PostingDTO> postingDTO = postingService.findAll();
		List<PostingspaymentDTO> postingspaymentDTO = postingPaymentService.orderbycost();
		for (int n = 0; n < (postingspaymentDTO.size()); n++) {
			for (int m = 0; m < postingDTOs.size(); m++) {
				System.out.println(postingspaymentDTO.get(n).getPostingsid());
				System.out.println(postingDTOs.get(m).getId());
				if (postingspaymentDTO.get(n).getPostingsid() == postingDTOs.get(m).getId()) {
					System.out.println("----abcde------");
					postingDTOVIPs.add(postingDTOs.get(m));
					postingDTO.remove(m);
				}
			}

		}
		modelMap.put("postingDTOVIPs", postingDTOVIPs);
		modelMap.put("postings", postingDTO);
		return "user/posting/index";

	}

	@GetMapping("/{id}")
	public String details(ModelMap modelMap, @PathVariable("id") int id, Authentication authentication, HttpSession httpSession) {
		PostingDTO posting = postingService.findById(id);
		modelMap.put("posting", posting);
		EmployerDTO employer = employerService.findbyname(posting.getEmployerName());
		modelMap.put("employer", employer);
//		
		if(authentication != null) {
			SeekerDTO seeker = seekerService.findByAccountID(accountService.findByUsername(authentication.getName()).getId());
			modelMap.put("seeker", seeker);
			ApplicationHistoryDTO applicationHistoryDTO = new ApplicationHistoryDTO();
			applicationHistoryDTO.setPostingID(posting.getId());
			applicationHistoryDTO.setSeekerID(seeker.getId());
			modelMap.put("applicationHistoryDTO", applicationHistoryDTO);
			return "user/posting/detailslogin";
		} else {
			return "user/posting/details";
		}
	}

	@PostMapping("/search")
	public String search(ModelMap modelMap, @RequestParam("local") Integer local,
			@RequestParam("experience") Integer experience, @RequestParam("title") String title,
			@RequestParam("wage") Integer wage, @RequestParam("type") Integer type,
			@RequestParam("category") Integer category) {
		if (local == -1) {
			local = null;
		}
		if (experience == -1) {
			experience = null;
		}
		if (wage == -1) {
			wage = null;
		}
		if (type == -1) {
			type = null;
		}
		if (category == -1) {
			category = null;
		}
		if (title == "") {
			title = null;
		}


		List<PostingDTO> postingDTOs = postingService.search(local, wage, type, category, experience, title);
		List<PostingDTO> postingDTOVIPs = new ArrayList<PostingDTO>();
	
		List<PostingDTO> postingDTO = postingService.search(local, wage, type, category, experience, title);
		List<PostingspaymentDTO> postingspaymentDTO = postingPaymentService.orderbycost();
		for (int n = 0; n < (postingspaymentDTO.size()); n++) {

			for (int m = 0; m < postingDTOs.size(); m++) {

				System.out.println(postingspaymentDTO.get(n).getPostingsid());
				System.out.println(postingDTOs.get(m).getId());

				if (postingspaymentDTO.get(n).getPostingsid() == postingDTOs.get(m).getId()) {

					System.out.println("----abcde------");
					postingDTOVIPs.add(postingDTOs.get(m));
					postingDTO.remove(m);
				}
			}

		}
		modelMap.put("postingDTOVIPs", postingDTOVIPs);
		modelMap.put("postings", postingDTO);

		return "user/posting/index";
	}
	@PostMapping("/applyCV/{id}")
	public String applyCV(ModelMap modelMap, @PathVariable("id") int id, Authentication authentication, RedirectAttributes redirectAttributes) {
	    PostingDTO posting = postingService.findById(id);
	    SeekerDTO seeker = seekerService.findByAccountID(accountService.findByUsername(authentication.getName()).getId());

	    ApplicationHistoryDTO applicationHistoryDTO = new ApplicationHistoryDTO();
	    applicationHistoryDTO.setPostingID(posting.getId());
	    applicationHistoryDTO.setSeekerID(seeker.getId());
	    applicationHistoryDTO.setStatus(0);
	    applicationHistoryDTO.setResult(0);

		if(applicationHistoryService.save(applicationHistoryDTO)) {
			redirectAttributes.addFlashAttribute("success", "Completed");
		} else {
            redirectAttributes.addFlashAttribute("error", "Thất bại...");
		}
		return "redirect:/home/posting/" + id;
		
	}
	
	@PostMapping("/saveJob/{id}")
	public String saveJob(ModelMap modelMap, @PathVariable("id") int id, Authentication authentication, RedirectAttributes redirectAttributes) {
	    PostingDTO posting = postingService.findById(id);
	    SeekerDTO seeker = seekerService.findByAccountID(accountService.findByUsername(authentication.getName()).getId());

	    ApplicationHistoryDTO applicationHistoryDTO = new ApplicationHistoryDTO();
	    applicationHistoryDTO.setPostingID(posting.getId());
	    applicationHistoryDTO.setSeekerID(seeker.getId());
	    applicationHistoryDTO.setStatus(2);
	    applicationHistoryDTO.setResult(0);

		if(applicationHistoryService.save(applicationHistoryDTO)) {
			redirectAttributes.addFlashAttribute("success2", "Completed");
		} else {
            redirectAttributes.addFlashAttribute("error2", "Thất bại...");
		}
		return "redirect:/home/posting/" + id;
		
	}

}
