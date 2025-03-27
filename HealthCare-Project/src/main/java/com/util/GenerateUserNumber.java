package com.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenerateUserNumber {

	public static String getRandomNumber(){
		LocalDateTime todaysDate = LocalDateTime.now();
		// 15-03-2025 //250315

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yymmdd");

		String format = todaysDate.format(dtf);

		System.out.println(format);

		Random number = new Random();

		int n = number.nextInt(9999) + 1000;

		String genNum = format + n;

		System.out.println(genNum);
		
		return genNum;
	}
}
