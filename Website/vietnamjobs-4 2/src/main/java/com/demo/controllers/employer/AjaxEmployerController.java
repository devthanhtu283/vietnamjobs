package com.demo.controllers.employer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dtos.ApplicationHistoryDTO;
import com.demo.services.ApplicationHistoryService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@RestController
@RequestMapping("ajax")
public class AjaxEmployerController {
	@Autowired
	private JavaMailSender sender;
	@Autowired
	private ApplicationHistoryService applicationHistoryService;
	
	@GetMapping(value = "viewCV", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ApplicationHistoryDTO viewCV(@RequestParam("seekerID") int seekerID, @RequestParam("appID") int appID) {
		ApplicationHistoryDTO applicationHistoryDTO = applicationHistoryService.findByID(appID);
		System.out.println(applicationHistoryDTO);
		applicationHistoryDTO.setStatus(1);
		applicationHistoryService.save(applicationHistoryDTO);
		return applicationHistoryDTO;
		
	}
	@GetMapping(value = "mailDialog", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ApplicationHistoryDTO mailDialog(@RequestParam("appID") int appID) {
		ApplicationHistoryDTO applicationHistoryDTO = applicationHistoryService.findByID(appID);
		return applicationHistoryDTO;
		
	}
	@PostMapping(value = "sendMailAcp", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public boolean sendMailAcp(@RequestParam("appID") int appID, @RequestParam("mailBody") String mailBody, @RequestParam("mailSubject") String mailSubject, @RequestParam("mailTo") String mailTo, @RequestParam("mailFrom") String mailFrom) {
		boolean status = true;
	
		MimeMessage mimeMessage = sender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
		try {
			messageHelper.setFrom(mailFrom);
			messageHelper.setTo(mailTo);
			messageHelper.setSubject(mailSubject);
			messageHelper.setText(mailBody, true);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ApplicationHistoryDTO applicationHistoryDTO = applicationHistoryService.findByID(appID);
		applicationHistoryDTO.setResult(1);
		applicationHistoryService.save(applicationHistoryDTO);
		sender.send(mimeMessage);
		return status;
		
	}
}
