package com.ServiceIMPL;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.Controller.LoginController;
import com.DTOs.DTO;
import com.Entity.Login;
import com.Entity.User;
import com.Example.Util.GenerateUserNum;
import com.Repository.LoginRepository;
import com.Service.LoginService;

@Service
public class LoginServiceIMPL implements LoginService {

	@Autowired
	private LoginRepository lr;

	DTO dto = new DTO();
	
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Override
	public DTO loginUserService(Login login) {

		if (login.getEmail() != null && login.getPassword() != null) {

			Login user = lr.findByEmailAndPassword(login.getEmail(), login.getPassword());

			if (user != null) {
				
				
				/*
				 * dto.setFirstname(user.getUser().getFirstname());
				 * dto.setAddress(user.getUser().getAddress());
				 * dto.setMobno(user.getUser().getMobno());
				 */
				 
				
				ModelMapper mm = new ModelMapper();
				mm.map(user.getUser(), dto);
				 
				return dto;

				// return mm.map(login, DTO.class);
			} else {
				dto.setMsg("User not registered!!");
				return dto;
			}

		} else {
			dto.setMsg("user and Email id should not be null!!");
			return dto;
		}
	}

	
}
