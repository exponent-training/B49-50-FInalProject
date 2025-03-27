package com.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenerateRandomNumber {
	public static void main(String args[])
	{
		
	}
	public static String generatenum()
	{
		LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String formattedDate = today.format(formatter);
        Random ran=new Random();
        int randomInt = ran.nextInt(10000); 
        String RandomNum=formattedDate+randomInt;
        System.out.println("random number generated: " + RandomNum);
        return RandomNum;
	}
	
	
}
