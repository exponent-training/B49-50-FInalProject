package com.Service;

import java.util.Optional;
import java.util.Scanner;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.DTOLayer.LoginDTO;
import com.DTOLayer.ResponseDTO;
import com.Entity.Login;
import com.Entity.User;
import com.example.util.GenerateRandomNo;
import com.repo.LoginRepo;
import com.repo.UserRepo;

@Service
public class LoginServiceIMPL implements LoginService {
	

	@Autowired
	public LoginRepo lr;
	@Autowired
	public UserRepo ur;
//   
	@Autowired
	private EmailService emss;
	
	private final LoginDTO ldto = new LoginDTO();

	@Autowired
	private ModelMapper modelMapper;
	
	User user=new User();
	
	Logger logger = LoggerFactory.getLogger(LoginServiceIMPL.class);
	
	@Override
	public ResponseDTO LoginPatientOrDoctor(Login login) {
		// TODO Auto-generated method stub
//         Login existingUser = lr.findByEmail(login.getEmail());
		
		Login existingUser=lr.findByEmailAndPassword(login.getEmail(),login.getPassword());
		
//		User existingUser = ur.findByEmail(login.getEmail());

		ResponseDTO RDTO = new ResponseDTO();
//		if (login.getUser() != null) {

		if(user!=null) {
			if (login.getEmail() != null) {

				if (existingUser == null) {
					
					login.getUser().setUserNumber(GenerateRandomNo.getRandomNumber());

					lr.save(login);
					logger.info("Login succesful");
					RDTO.setMsg("User Added Successfully");

					return RDTO;

				} else {

					logger.info("User is Alresdy present");
					RDTO.setMsg("User Alredy present");
					return RDTO;
				}
			} else {
				logger.warn("email is empty");
				RDTO.setMsg("Email is empty");
				return RDTO;

			}
		} else {
			logger.warn("user should not be null");
			RDTO.setMsg("User should not be null");
			return RDTO;
		}
	}


	

	@Override
	public LoginDTO getLogindetails(Login login) {

		if (login != null && login.getEmail() != null) {
			Login details = lr.findByEmailAndPassword(login.getEmail(), login.getPassword());

			if (!login.getPassword().equals(details.getPassword())) {

				ldto.setMsg("The password is wrong, enter your password agian");
//			    LoginServiceIMPL limpl=new LoginServiceIMPL();
//			    limpl.getLogindetails(login);

				return ldto;

			}

			if (details != null) {
//				ldto.setFirstName(details.getUser().getFirstName());
//				ldto.setLastName(details.getUser().getLastName());
//				ldto.setMobNumber(details.getUser().getMobNumber());
//				ldto.setAddress(details.getUser().getAddress());

				/*
				 * here we use modelmapper instead of DTO by which we can get all data by
				 * creating and calling one object only one time no need to call object for
				 * different property
				 */

				LoginDTO dto = modelMapper.map(details.getUser(), LoginDTO.class);
//				

				return dto;

			} else {

				ldto.setMsg("Credentials invalid");
				return ldto;
			}
		} else {

			ldto.setMsg("Contact your admin email is empty");
			return ldto;
		}
	}

	@Override
	public User updateUserthroughLogin(String email, User user) {
		
		Login login=new Login();
		Login details = lr.findByEmailAndPassword(login.getEmail(), login.getPassword());
       
          if (user == null) {
            throw new RuntimeException("User not found");
        }

        // ✅ Fix: Update user details using login or actual data
        if (login.getUser() != null) {
            user.setFirstName(login.getUser().getFirstName());
            user.setLastName(login.getUser().getLastName());
            user.setAddress(login.getUser().getAddress());
        }

        // Save updated user
        return ur.save(user);
    }

	

	}

