package com.demo.controllers.employer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.dtos.AccountDTO;
import com.demo.dtos.EmployerDTO;
import com.demo.dtos.PostingDTO;
import com.demo.dtos.PostingspaymentDTO;
import com.demo.entities.Experience;
import com.demo.entities.Wage;
import com.demo.helpers.CheckDateHelper;
import com.demo.services.AccountService;
import com.demo.services.CategoryService;
import com.demo.services.EmployerService;
import com.demo.services.ExperienceService;
import com.demo.services.PostingPaymentService;
import com.demo.services.PostingService;
import com.demo.services.WageService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping({ "employer/posting" })
public class PostingEmployerController {

	@Autowired
	private EmployerService employerService;
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AccountService accountService;
	@Autowired
	private PostingService postingService;
	@Autowired
	private WageService wageService;
	@Autowired
	private ExperienceService experienceService;
	@Autowired
	private PostingPaymentService postingPaymentService;

	@GetMapping({ "add" })
	public String add(ModelMap modelMap, Authentication authentication) {
		PostingDTO postingDTO = new PostingDTO();
		modelMap.put("postingDTO", postingDTO);

		return "employer/posting/add";

	}
/*
	@PostMapping("add")
	public String add(@ModelAttribute PostingDTO postingDTO,@RequestParam("deadlineString") String deadlineString, @RequestParam("inputGender") String inputGender,
			@RequestParam("gender") String gender, @RequestParam("inputWage") String inputWage,
			@RequestParam("minWage") String minWage, @RequestParam("maxWage") String maxWage,
			@RequestParam("experience") String experience, @RequestParam("otherexperience") String otherexperience,
			RedirectAttributes redirectAttributes, Authentication authentication) {

		EmployerDTO employerDTO = employerService.findByUsername(authentication.getName());
		postingDTO.setEmployerLogo(employerDTO.getLogo());
		postingDTO.setEmployerName(employerDTO.getName());
		postingDTO.setStatus(true);
		postingDTO.setCreated(new Date());
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			postingDTO.setDealine(simpleDateFormat.parse(deadlineString));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (inputGender.toString().trim().equalsIgnoreCase("0")) {
			postingDTO.setGender(gender);
			System.out.println(gender);
		} else {
			postingDTO.setGender(inputGender);
		}

		if (experience.toString().trim().equalsIgnoreCase("0")) {
			Experience experience2 = new Experience(otherexperience, true);
			experienceService.save(experience2);
			postingDTO.setExpName(experience2.getName());
		} else {
			postingDTO.setExpName(experience);
		}

		if (inputWage.toString().trim().equalsIgnoreCase("0")) {
			Wage wage = new Wage(minWage + " - " + maxWage + " trieu.", Integer.parseInt(minWage),
					Integer.parseInt(maxWage), true);
			wageService.save(wage);
			postingDTO.setWageName(wage.getName());
		} else {
			postingDTO.setWageName(inputWage);
		}
		if (postingService.save(postingDTO)) {
			redirectAttributes.addFlashAttribute("success", "Add Posting Completed");
			return "redirect:/employer/dashboard/list";

		} else {
			redirectAttributes.addFlashAttribute("error", "Failed 3");
			return "redirect:/employer/posting/add";
		}

	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {

		if (postingService.delete(id)) {
			redirectAttributes.addFlashAttribute("success", "Thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Thất bại...");
		}

		return "redirect:/employer/dashboard/list";
	}

	@GetMapping({ "edit/{id}" })
	public String edit(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.put("postingDTO", postingService.findById(id));

		return "employer/posting/edit";
	}

	@PostMapping("edit")
	public String update(@ModelAttribute PostingDTO postingDTO, @RequestParam("newDeadline") String newDeadline,
			RedirectAttributes redirectAttributes) {
		PostingDTO postingDTO2 = postingService.findById(postingDTO.getId());
		postingDTO.setCreated(postingDTO2.getCreated());

		if (!newDeadline.isEmpty()) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			try {
				postingDTO.setDealine(simpleDateFormat.parse(newDeadline));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			postingDTO.setDealine(postingDTO2.getDealine());
		}
		if (postingService.save(postingDTO)) {
			redirectAttributes.addFlashAttribute("success", "Thành công!");
			return "redirect:/employer/dashboard/list";
		} else {
			redirectAttributes.addFlashAttribute("error", "Thất bại...");
			return "redirect:/employer/posting/edit/" + postingDTO.getId();
		}

	}*/

	@GetMapping({ "payment/{postingid}" })
	public String payment(ModelMap modelMap, @PathVariable("postingid") int postingid, Authentication authentication) {
		PostingspaymentDTO postingspaymentDTO = new PostingspaymentDTO();
		modelMap.put("postingspaymentDTO", postingspaymentDTO);
		modelMap.put("postingid", postingid);
		String category = categoryService.findbyname(postingService.findById(postingid).getCategoryName()).getName();
		modelMap.put("employer", employerService.findbyname(postingService.findById(postingid).getEmployerName()));
		modelMap.put("wallet", accountService.findByUsername(authentication.getName()).getWallet());
		modelMap.put("mostpayment", postingPaymentService.limitbycategory(true, category, 3));

		List<PostingspaymentDTO> postingpays = postingPaymentService.findbypostingsid(postingid, true);
		if (!postingpays.isEmpty()) {
			CheckDateHelper chekCheckDateHelper = new CheckDateHelper();
			String result = chekCheckDateHelper.checkdeadlinePostingPayment(postingpays.get(0));
			modelMap.put("result", result);
		}

		return "employer/posting/paymentpage";

	}

	@PostMapping({ "payment" })
	public String payment(@ModelAttribute PostingspaymentDTO postingspaymentDTO, @RequestParam("postingid") int postingid, RedirectAttributes redirectAttributes,
			Authentication authentication) {
		postingspaymentDTO.setStatus(true);
		postingspaymentDTO.setCreated(new Date());
		postingspaymentDTO.setPostingsid(postingid);
		List<PostingspaymentDTO> postingspaymentDTOs = postingPaymentService
				.findbypostingsid(postingspaymentDTO.getPostingsid(), true);
			if (postingspaymentDTOs.isEmpty()) {
				if (postingPaymentService.save(postingspaymentDTO)) {
					AccountDTO accountDTO = accountService.findByUsername(authentication.getName());
					double newwallet = accountDTO.getWallet()
							- (postingspaymentDTO.getCost() * postingspaymentDTO.getDuration());
					
					if(newwallet > 0) {
						accountDTO.setWallet(newwallet);
						accountService.save(accountDTO);
						redirectAttributes.addFlashAttribute("success", "Add Posting Completed");
						return "redirect:/employer/job";
					} else {
						redirectAttributes.addFlashAttribute("error", "Failed 3");
						return "redirect:/employer/posting/payment/" + postingspaymentDTO.getPostingsid();
					}

				} else {
					redirectAttributes.addFlashAttribute("error", "Failed 3");
					return "redirect:/employer/posting/payment/" + postingspaymentDTO.getPostingsid();
				}
			} else {
				PostingspaymentDTO postingspaymentDTO2 =  postingspaymentDTOs.get(0);
				
				if(postingspaymentDTO2.getCost() <=  postingspaymentDTO.getCost()) {
					CheckDateHelper chekCheckDateHelper = new CheckDateHelper();
					
					postingspaymentDTO2.setStatus(false);
					
					double lefttotal = chekCheckDateHelper.countLeftDayPaymentbyDate(postingspaymentDTO2) * postingspaymentDTO2.getCost() * 0.5;
					
					postingspaymentDTO2.setDuration(postingspaymentDTO2.getDuration() - chekCheckDateHelper.countLeftDayPaymentbyDate(postingspaymentDTO2));
					
					AccountDTO accountDTO = accountService.findByUsername(authentication.getName());
					accountDTO.setWallet(accountDTO.getWallet() + lefttotal);
				
					
					if (postingPaymentService.save(postingspaymentDTO) &&  accountService.save(accountDTO) && postingPaymentService.save(postingspaymentDTO2))  {
						AccountDTO accountDTO2 = accountService.findByUsername(authentication.getName());
						double newwallet = accountDTO2.getWallet()
								- (postingspaymentDTO.getCost() * postingspaymentDTO.getDuration());
						accountDTO2.setWallet(newwallet);
						accountService.save(accountDTO2);
						redirectAttributes.addFlashAttribute("success", "Completed");
						return "redirect:/employer/job";

					} else {
						redirectAttributes.addFlashAttribute("error1", "Failed 3");
						return "redirect:/employer/posting/payment/" + postingspaymentDTO.getPostingsid();
					}
				} else {
					redirectAttributes.addFlashAttribute("error2", "Failed 3");
					return "redirect:/employer/posting/payment/" + postingspaymentDTO.getPostingsid();
				}
				 

			}
		
	}

}

