package com.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.SingleAdmin;
import com.Entity.roles;
import com.Impl.UserServiceImpl;
import com.repository.rolerepo;
import com.service.roleserv;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/role")
public class rolecontroller {
	@Autowired
	private roleserv rs;
	@Autowired
	private rolerepo rr;
	@Autowired
	private JavaMailSender jms;

	Logger logg = (Logger) LoggerFactory.getLogger(rolecontroller.class);

	@PostMapping("/addrolebyadmin")
	public ResponseEntity<?> addrole(@RequestParam("aemail") String ae, @RequestParam("apass") String ap,
			@RequestParam("roleName") String role) {
		if (ae != null && ap != null && role != null) {
			if (ae.equals("laxmiadmin@gmail.com") && ap.equals("laxmi1234")) {
				logg.info("admin login successfull now you can add role");
				roles findByRoleName = rr.findByRoleName(role);
				if (findByRoleName != null) {
					return new ResponseEntity("role already present", HttpStatus.METHOD_NOT_ALLOWED);
				}
				roles r = new roles();
				r.setRoleName(role);
				SimpleMailMessage sms = new SimpleMailMessage();
				sms.setTo("laxmi5408@gmail.com");
				sms.setSubject("Admin Access to change role");
				sms.setText("I added role=" + role);
				jms.send(sms);
				logg.info("data emp addes and mail sended");
				rr.save(r);
				return new ResponseEntity("role added", HttpStatus.CREATED);
			} else {
				return new ResponseEntity("admin login unsuccessfull ", HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity("all feilds mandatory", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@DeleteMapping("/deleterolebyadmin")
	public ResponseEntity<?> deleterole(@RequestParam("aemail") String ae, @RequestParam("apass") String ap,
			@RequestParam("roleName") String role) {
		if (ae != null && ap != null && role != null) {
			if (ae.equals("laxmiadmin@gmail.com") && ap.equals("laxmi1234")) {
				logg.info("admin login successfull now you can add role");
				roles findByRoleName = rr.findByRoleName(role);
				if (findByRoleName != null) {
					rr.delete(findByRoleName);
				}
				SimpleMailMessage sms = new SimpleMailMessage();
				sms.setTo("laxmi5408@gmail.com");
				sms.setSubject("Admin Access to delete role");
				sms.setText("I delete role=" + role);
				jms.send(sms);
				logg.info("role deleted and mail sended");
				return new ResponseEntity("role delete", HttpStatus.OK);
			} else {
				return new ResponseEntity("admin login unsuccessfull ", HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity("all feilds mandatory", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping("/getallrolebyadmin")
	public ResponseEntity<?> getrole(@RequestParam("aemail") String ae, @RequestParam("apass") String ap) {
		if (ae != null && ap != null) {
			if (ae.equals("laxmiadmin@gmail.com") && ap.equals("laxmi1234")) {
				logg.info("admin login successfull now you can add role");
				List<roles> findAll = rr.findAll();
				return new ResponseEntity(findAll, HttpStatus.OK);
			} else {
				return new ResponseEntity("admin login unsuccessfull ", HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity("all feilds mandatory", HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
