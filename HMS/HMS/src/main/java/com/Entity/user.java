package com.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class user {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  int uid;
	private String unum;
	private String ufname;
	private String ulname;
	private String uadd;
	private int uzip;
	private String ucountry;
	private char ugender;
	private long umobile;
	@OneToOne(cascade = CascadeType.DETACH)
	private roles urole;
	 @JsonBackReference
	@OneToOne(cascade = CascadeType.ALL)
	private login log;
}
