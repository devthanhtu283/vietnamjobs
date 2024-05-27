package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.stereotype.Service;


import jakarta.mail.internet.MimeMessage;

@Service("mailService")
public class MailServiceImpl implements MailService{
	@Autowired
	private JavaMailSender sender;
	
	@Override
	public boolean sendMailAccuracy(String from, String to, String content) {
		try {
			String subject = "Email Xác thực thông tin tài khoản ứng viên";
			MimeMessage mimeMessage = sender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom(from);
			messageHelper.setTo(to);
			messageHelper.setSubject(subject);
			messageHelper.setText(content, true);
			sender.send(mimeMessage);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	
	
	
}
