package com.example.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenerateRandomNo {
	
	public static String getRandomNumber() {
		
	LocalDateTime todaysDate= LocalDateTime.now();
	
	DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyMMDD");
	
	 String format=todaysDate.format(dtf);
	 
	 Random number=new Random();
	 
	 int n=number.nextInt(9999)+1000;
	 
	 String genNo=format+n;
	 
	 return genNo;
	}

}
