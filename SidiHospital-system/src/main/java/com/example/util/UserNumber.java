package com.example.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;



public class UserNumber {
	public static String  getrandomnuber() {
		LocalDateTime todaytime=LocalDateTime.now();
		DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyMMdd");
		String format=todaytime.format(dtf);
		Random number=new Random();
		int n=number.nextInt(99)+10;
		String randomnumber=format+n;
		
		
		return randomnumber;
	}
	

}
