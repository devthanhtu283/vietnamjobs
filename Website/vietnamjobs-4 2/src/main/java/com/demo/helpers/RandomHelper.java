package com.demo.helpers;

import java.util.Random;
import java.util.UUID;

public class RandomHelper {
	
	private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789"; // 0-9
    private static final String specials = "~=+%^*/()[]{}/!@#$?|";
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
    private static final String ALL = alpha + alphaUpperCase + digits + specials;
    private static Random generator = new Random();
 

	public static String random() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	 public static int randomNumber(int min, int max) {
	        return generator.nextInt((max - min) + 1) + min;
	    }
	
	 public String randomAlphaNumeric(int numberOfCharactor) {
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < numberOfCharactor; i++) {
	            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
	            char ch = ALPHA_NUMERIC.charAt(number);
	            sb.append(ch);
	        }
	        return sb.toString();
	    }

}
