package com.Example.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenerateUserNum {

	public static String generateNum() {
	LocalDateTime ldt	=LocalDateTime.now();
	
	//it is use for formatting date
	DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yymmdd");
	
	String format =ldt.format(dtf);
	
	//it use to generate random number 
	Random num = new Random();
	int n=num.nextInt(9999)+1000;
	
	String randomnum= format+n;
	
	return randomnum;
	}
}
