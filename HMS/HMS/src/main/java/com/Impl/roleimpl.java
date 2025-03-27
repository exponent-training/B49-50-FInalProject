package com.Impl;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.Entity.roles;
import com.Entity.user;
import com.repository.rolerepo;
import com.service.roleserv;
@Service
public class roleimpl implements roleserv{
@Autowired
	public rolerepo rr;
	

Logger logger = LoggerFactory.getLogger(roleimpl.class);

	@Override
	public ResponseEntity<?> roleexist(roles role) {
	roles findByRoleName = rr.findByRoleName(role.getRoleName());
		if(findByRoleName!=null)
		{return new ResponseEntity("role already present",HttpStatus.METHOD_NOT_ALLOWED);
				}
		else
		{
			return new ResponseEntity("role added",HttpStatus.CREATED);
		}
	}

}
