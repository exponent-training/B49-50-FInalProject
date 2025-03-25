package com.example.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenerateUserNumber {
	
	public static String getrandomNumber() {

		LocalDateTime todayDate = LocalDateTime.now();

		// 15-03-2025, yyMMdd--> 250315

		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat(); sdf.format("yyMMdd");
		 */

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMdd");

		String format = todayDate.format(dtf);

		// System.out.println(format);

		Random number = new Random();

		int n = number.nextInt(9999) + 1000;

		String genNumber = format + n;

		//System.out.println(genNumber);
		
		return genNumber;

	}
	

}
