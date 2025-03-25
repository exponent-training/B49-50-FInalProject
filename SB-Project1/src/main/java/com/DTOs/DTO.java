package com.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value=Include.NON_NULL)
public class DTO {

	private String firstname;
	
	private String address;
	
	private long mobno;
	
	private String msg;
}
