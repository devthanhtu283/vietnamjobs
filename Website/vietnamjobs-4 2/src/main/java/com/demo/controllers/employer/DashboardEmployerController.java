package com.demo.controllers.employer;

import java.time.LocalDate;
import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.services.AccountService;
import com.demo.services.ApplicationHistoryService;
import com.demo.services.EmployerService;
import com.demo.services.PostingService;

@Controller
@RequestMapping({"employer/dashboard" })
public class DashboardEmployerController {

	 @Autowired
	    private EmployerService companyService;

	    @Autowired
	    private ApplicationHistoryService applicationHistoryService;

	    @Autowired
	    private AccountService accountService;

	    @Autowired
	    private PostingService postingService;
	 @GetMapping(value = {"/index", "", "/"})
	    private String index(ModelMap map) {

	        int currentYear = Year.now().getValue();
	        int currentMonth = LocalDate.now().getMonthValue();

	        int applyPass = applicationHistoryService.countByResult(1);
	        int applyFail = applicationHistoryService.countByResult(2);


	        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	        for (int i = 0; i < months.length; i++) {
	            int month = i + 1;
	            map.put("company" + months[i], accountService.countByRoleAndMonthAndYear(2, month, currentYear));
	        }

	        for (int i = 0; i < months.length; i++) {
	            int month = i + 1;
	            map.put("seeker" + months[i], accountService.countByRoleAndMonthAndYear(1, month, currentYear));
	        }

	        for (int i = 0; i < months.length; i++) {
	            int month = i + 1;
	            map.put("post" + months[i], postingService.countByMonthAndYear(month, currentYear));
	        }

	        for (int i = 0; i < months.length; i++) {
	            int month = i + 1;
	            map.put("apply" + months[i], applicationHistoryService.countByMonthAndYear(month, currentYear));
	        }


	        map.put("applyPass", applyPass);
	        map.put("applyFail", applyFail);
	        map.put("company", companyService.findTop(5));


	        return "employer/dashboard/index";
	 }

}
