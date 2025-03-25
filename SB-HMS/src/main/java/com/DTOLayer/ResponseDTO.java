package com.DTOLayer;

import lombok.Data;

@Data
public class ResponseDTO {

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
