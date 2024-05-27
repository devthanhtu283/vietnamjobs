package com.demo.helpers;

import java.util.UUID;

public class FileHelper {

	public static String generateFileName(String fileName) {
		String name = UUID.randomUUID().toString().replace("-", "");
		int lastIndex = fileName.lastIndexOf(".");
		return name + fileName.substring(lastIndex);
	}
	
}
