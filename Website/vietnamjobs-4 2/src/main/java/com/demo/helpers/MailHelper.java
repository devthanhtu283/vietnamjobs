package com.demo.helpers;

import java.util.UUID;

import com.demo.dtos.AccountDTO;

public class MailHelper {

	public static String mailhelper(AccountDTO accountDTO) {
		String result = "Your NewPassword is " + accountDTO.getPassword();
		return result;
	}
	
}
