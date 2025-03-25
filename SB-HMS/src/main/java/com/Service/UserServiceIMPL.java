package com.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.DTOLayer.ResponseDTO;
import com.Entity.Login;
import com.Entity.User;
import com.example.util.GenerateRandomNo;
import com.repo.LoginRepo;
import com.repo.UserRepo;

@Transactional
@Service
public class UserServiceIMPL implements UserService {
	
	@Autowired
	private UserRepo UR;
	@Autowired
	private LoginRepo lr;
	 private final ModelMapper modelMapper;

	Logger logger = LoggerFactory.getLogger(UserServiceIMPL.class);
	
	@Autowired
    public UserServiceIMPL(LoginRepo lr, ModelMapper modelMapper) {
        this.lr = lr;
        this.modelMapper = modelMapper;
    }
  
//	User user=new User();

@Override
	public ResponseDTO LoginPatientOrDoctor(User user) {
//		User existingUser = UR.findByEmail(user.getEmail());
		
//		Login existingUser=lr.findByEmailAndPassword(login.getEmail(),login.getPassword());
		ResponseDTO RDTO = new ResponseDTO();
		if (user != null) {
//
			

//				
//					
					user.setUserNumber(GenerateRandomNo.getRandomNumber());
//
					UR.save(user);
					logger.info("User Register Successfully");
					RDTO.setMsg("User Added Successfully");

					return RDTO;
//
				} else {
//
					logger.info("Emial should not to be null");
					RDTO.setMsg("Email shoult not be null");
					return RDTO;
				}
			} 
			}
//		
		
	



