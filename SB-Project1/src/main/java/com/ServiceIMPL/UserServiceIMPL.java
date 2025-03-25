package com.ServiceIMPL;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.Controller.UserController;
import com.Entity.Login;
import com.Entity.Roles;
import com.Entity.User;
import com.Example.Util.GenerateUserNum;
import com.Repository.LoginRepository;
import com.Repository.RolesRepository;
import com.Repository.UserRepository;
import com.Service.UserService;

@Service
public class UserServiceIMPL implements UserService {

	@Autowired
	private UserRepository ur;

	@Autowired
	private LoginRepository lr;
	
	@Autowired
	private JavaMailSender jms;
	
	@Autowired
	private RolesRepository rr;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Override
	public int addUserinService(Login login) {

		if (login.getUser() != null && login.getEmail() != null && !login.getEmail().isEmpty()) {
			Login user = lr.findByEmail(login.getEmail());
			if (user!=null) {
				logger.warn("User allready exist");
				return 1;
			} else {
				
				login.getUser().setUsernumber(GenerateUserNum.generateNum());
				
                Roles roles=rr.findByRolename("normaluser");
				login.getUser().setRole(roles);
				lr.save(login);
				return 2;
			}
		} else {
			logger.warn("user or email should not be null");
			return 3;
		}

	}
	@Override
	public  int newPasswordInService(Login login) {

		if (login.getEmail() != null) {

			Login user = lr.findByEmail(login.getEmail());

			if (user != null) {

				String otp = GenerateUserNum.generateNum();
				user.setOtp(otp);
				lr.save(user);
				
				SimpleMailMessage sms = new SimpleMailMessage();
				sms.setTo(user.getEmail());
				sms.setSubject("OTP!!");
				
				sms.setText(otp);

				jms.send(sms);

				return 1;
			} else {

				return 2;
			}

		}
		return 0;

	}

	@Override
	public int updatePasswordInService(String otp,String password) {

		Login user = lr.findByOtp(otp);
		if (user != null) {
			user.setPassword(password);
			lr.save(user);
			logger.info("Password updated!!");
			return 1;
		} else {
			logger.warn("Incorrect OTP!!");
			return 2;
		}
	}

	@Override
	public int updateUserInSerive(Login login) {

         Login user= lr.findByEmail(login.getEmail());
         
         if(user!=null) {
        	User us= user.getUser();
        	User us2=login.getUser();
        	
        	us.setFirstname(us2.getFirstname());
        	us.setLastname(us2.getLastname());
        	us.setMobno(us2.getMobno());
        	us.setAddress(us2.getAddress());
        	us.setZipcode(us2.getZipcode());
        	user.setUser(us);
          	 lr.save(user);
        	 logger.info("User Updated!!");
        	 return 1;
         }else {
        	 
        	 logger.warn("worng emailID!!");
             return 0;
         }
	}

	@Override
	public int deleteUserInSerivce(String email) {
        
		Login user =lr.findByEmail(email);
		
		if(user!=null) {
	      lr.delete(user);
	      logger.info("User deleted successfull");
	      return 1;
		}else {
			logger.warn("Please enter correct Email");
			return 0;
		}
		
	}

}
