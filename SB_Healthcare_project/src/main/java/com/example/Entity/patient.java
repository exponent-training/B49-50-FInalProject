package com.example.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class patient {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int pid;
	private String pname;
	private int pnumber;
	private String email;
	private String address;
	
	
	
}
