package com.employeeApi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomGeneratorUtil {
	
	public static String getRandomEmpName() {
		String randomString = RandomStringUtils.randomAlphabetic(3);
		return ("John"+randomString);

	}
	
	public static String getRandomEmpSal() {
		String randomString = RandomStringUtils.randomNumeric(5);
		return randomString;

	}
	
	public static String getRandomEmpAge() {
		String randomString = RandomStringUtils.randomNumeric(2);
		return randomString;

	}

}
