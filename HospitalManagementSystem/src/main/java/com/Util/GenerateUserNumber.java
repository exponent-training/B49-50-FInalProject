package com.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenerateUserNumber {

	public static String getRandomNumber() {

		LocalDateTime todayDate = LocalDateTime.now();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMdd");
		String format = todayDate.format(dtf);
		Random random = new Random();
		int randomNumber = 1000 + random.nextInt(9000);
		String result = format + randomNumber;

		return result;

	}

}
