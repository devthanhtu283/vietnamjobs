package com.demo.helpers;

import java.util.Calendar;
import java.util.UUID;

import com.demo.dtos.PostingDTO;
import com.demo.dtos.PostingspaymentDTO;

public class CheckDateHelper {
	

	public static String generateFileName(String fileName) {
		String name = UUID.randomUUID().toString().replace("-", "");
		int lastIndex = fileName.lastIndexOf(".");
		return name + fileName.substring(lastIndex);
	}

	

	public static boolean updatePostingPaymentbyDate(PostingspaymentDTO postingspaymentDTO) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(postingspaymentDTO.getCreated());
		int deadline = calendar.get(Calendar.DAY_OF_YEAR) + postingspaymentDTO.getDuration();
		Calendar calendartoday = Calendar.getInstance();
		int today = calendartoday.get(Calendar.DAY_OF_YEAR);
        return today - deadline < 0;
		
	}
	
	public static int countLeftDayPaymentbyDate(PostingspaymentDTO postingspaymentDTO) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(postingspaymentDTO.getCreated());
		int deadline = calendar.get(Calendar.DAY_OF_YEAR) + postingspaymentDTO.getDuration();
		Calendar calendartoday = Calendar.getInstance();
		int today = calendartoday.get(Calendar.DAY_OF_YEAR);
        return  deadline - today;
		
	}
	
	public static boolean updatePostingbyDate(PostingDTO postingDTO) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(postingDTO.getDealine());
		int deadline = calendar.get(Calendar.DAY_OF_YEAR);
		Calendar calendartoday = Calendar.getInstance();
		int today = calendartoday.get(Calendar.DAY_OF_YEAR);
        return today - deadline < 0;
		
	}

	public static String checkdeadlinePostingPayment(PostingspaymentDTO postingspaymentDTO) {
		String result = "";

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(postingspaymentDTO.getCreated());
		int deadline = calendar.get(Calendar.DAY_OF_YEAR) + postingspaymentDTO.getDuration();

		Calendar calendartoday = Calendar.getInstance();
		int today = calendartoday.get(Calendar.DAY_OF_YEAR);
		result = " The post upgrade deadline is " + (deadline - today)
				+ " days left. Creating a new one will delete the old deadline and refresh it according to the current time. The amount of the remaining time will be refunded 50%.";
		return result;
	}

}
