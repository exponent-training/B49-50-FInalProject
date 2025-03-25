package com.Utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RandomNumberGenerator {

	public static String generateNumber() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		String format = date.format(formatter);
		int randomNumber = (int) (Math.random() * 10000);
		return format+randomNumber;
	}

}
