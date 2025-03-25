package com.example.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class LoginDTO
{
	private String username;
	
	private String mobNumber;
	
	private String address;
	
	private String msg;
	
}
