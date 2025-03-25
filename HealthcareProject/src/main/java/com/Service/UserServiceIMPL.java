package com.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DTO.ResponseDTO;
import com.Entity.Login;
import com.Entity.PasswordResetToken;
import com.Entity.Roles;
import com.Entity.User;
import com.Repository.LoginRepository;
import com.Repository.PasswordResetTokenRepository;
import com.Repository.RoleRepository;
import com.Repository.UserRepository;
import com.Util.GenerateUserNumber;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

@Service
public class UserServiceIMPL implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceIMPL.class);

	@Autowired
	private UserRepository ur;

	@Autowired
	private PasswordResetTokenRepository prtr;

	@Autowired
	private RoleRepository rr;

	@Autowired
	private LoginRepository lr;

	@Autowired
	private JavaMailSender jms;

	@Override
	public ResponseDTO userRegisterInService(Login login) {

		ResponseDTO response = new ResponseDTO();

		Login userfromDB = lr.findByEmail(login.getEmail());

		if (login != null && login.getEmail() != null) {

			if (userfromDB == null) {

				login.getUser().setUserNumber(GenerateUserNumber.getrandomNumber());

				Roles roles = rr.findByRoleName("NormalUser");

				User user = login.getUser();
				user.setRoles(roles);

				// ur.save(user);

				lr.save(login);

				response.setMsg("User added");

				SimpleMailMessage sms = new SimpleMailMessage();
				sms.setTo("bhalegharesakshi@gmail.com");
				sms.setSubject("Registration completed");
				sms.setText("Your username is :" + login.getEmail() + " " + "Your password is :" + login.getPassword());

				jms.send(sms);

				response.setMsg("Mail sended");

				return response;

			} else {
				response.setMsg("User already existed");
				return response;
			}
		} else {
			response.setMsg("User not registered beacause email is empty");
		}
		return response;
	}

	// check if email is registred
	public boolean isEmailRegistered(String email) {
		return ur.existsByEmail(email);

	}

	// Create and store the password reset token

	public String createPasswordResetToken(String email) {
		String token = UUID.randomUUID().toString();

		// Store the token with the associated email

		PasswordResetToken resetToken = new PasswordResetToken();

		resetToken.setEmail(email);
		resetToken.setToken(token);

		prtr.save(resetToken);

		return token;

	}

	public User updateUserByEmail(String email, User updatedUser) {
		Optional<User> optionalUser = ur.findUserByEmail(email);

		if (optionalUser.isPresent()) {
			User existingUser = optionalUser.get();
			existingUser.setFirstName(updatedUser.getFirstName());
			existingUser.setMobileno(updatedUser.getMobileno());

			return ur.save(existingUser);
		} else {
			throw new RuntimeException("User notroles found with email: " + email);
		}
	}

	public void deleteUserByEmail(String email) {

		Optional<User> userOptional = ur.findUserByEmail(email);

		if (userOptional.isPresent()) {
			ur.delete(userOptional.get());
			logger.info("User deleted");
		} else {
			throw new RuntimeException("User not found with email: " + email);
		}
	}

}
