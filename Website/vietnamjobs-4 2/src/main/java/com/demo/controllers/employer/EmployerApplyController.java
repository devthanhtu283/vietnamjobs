package com.demo.controllers.employer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.dtos.ApplicationHistoryDTO;
import com.demo.entities.ApplicationHistory;
import com.demo.services.ApplicationHistoryService;

@Controller
@RequestMapping({"employer/apply" })
public class EmployerApplyController {

	@Autowired
	private ApplicationHistoryService applicationHistoryService;

	@GetMapping({"index/{id}", "/{id}"})
	public String job(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.put("id", id);
		return "employer/apply/job";
	}
	@GetMapping({"reject/{id}"})
	public String reject(@PathVariable("id") int id, ModelMap modelMap, RedirectAttributes redirectAttributes) {
		ApplicationHistoryDTO applicationHistory = applicationHistoryService.findByID(id);
		applicationHistory.setResult(2);
		if(applicationHistoryService.save(applicationHistory)) {
			redirectAttributes.addFlashAttribute("success", "Thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Thất bại...");
		}

		applicationHistoryService.save(applicationHistory);
		modelMap.put("id", id);
		return "redirect:/employer/apply/index/" + applicationHistory.getPostingID();
	}


}
