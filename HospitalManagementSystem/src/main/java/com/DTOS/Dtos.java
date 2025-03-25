package com.DTOS;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class Dtos {

	private String usernumber;
	private String address;
	private String mobileno;
	private String msg;

}
