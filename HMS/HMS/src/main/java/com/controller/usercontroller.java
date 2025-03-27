package com.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.loginDTO;
import com.DTO.responseDTO;
import com.Entity.login;
import com.Entity.user;
import com.Impl.UserServiceImpl;
import com.repository.loginrepo;
import com.service.UserServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class usercontroller {
	@Autowired
	public UserServices ser;
	@Autowired
	public loginrepo lr;

	Logger logger = LoggerFactory.getLogger(usercontroller.class);

	@PostMapping("/reg")
	public ResponseEntity<?> addUser(@RequestBody login log) {
		if (log != null) {
			ResponseEntity<?> adduser = ser.adduser(log);
			return new ResponseEntity(adduser, HttpStatus.OK);
		} else {
			return new ResponseEntity("user empty", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/forgetpass") //
	public ResponseEntity<?> forgetPassword(@RequestParam("uemail") String ue, @RequestParam("umobile") long um,
			@RequestParam("upass") String newup) {

		login loginuser = lr.findByUemail(ue);
		long umobile = loginuser.getUsers().getUmobile();
		if (loginuser.getUsers().getUmobile() == um) {
			logger.info("You are in updation of password");
			ser.updatepass(loginuser, newup);
		}
		return new ResponseEntity("Passowrd Updated", HttpStatus.ACCEPTED);
	}

	@GetMapping("/getuserdetialsbyemail") // all details to pass for updation
	public ResponseEntity<login> getdetails(@RequestParam("uemail") String ue, @RequestParam("upass") String up) {
		login loginuser = lr.findByUemail(ue);
		if (loginuser!=null) {
			if (loginuser.getUpass().equals(up)) {
				logger.info("Login Successfully : Update data");
				return new ResponseEntity(loginuser, HttpStatus.OK);
			} else {
				return new ResponseEntity("password Incorrect", HttpStatus.EXPECTATION_FAILED);
			}
		} else {
			return new ResponseEntity("Invalid user email", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/GetUserPartialDetialsByEmail") // after login partial result will display
	public ResponseEntity<loginDTO> getdetailsDTO(@RequestParam("uemail") String ue, @RequestParam("upass") String up) {
		loginDTO ld = new loginDTO();
		login loginuser = lr.findByUemail(ue);
		if (loginuser!=null) {
			if (loginuser.getUpass().equals(up)) {
				logger.info("Login Successfully : Update data");

				ld.setUdfname(loginuser.getUsers().getUfname());
				ld.setUdlname(loginuser.getUsers().getUlname());
				ld.setUdmobile(loginuser.getUsers().getUmobile());
				ld.setUdnum(loginuser.getUsers().getUnum());
				ld.setUdrolename(loginuser.getUsers().getUrole().getRoleName());
				return new ResponseEntity(ld, HttpStatus.OK);
			} else {
				return new ResponseEntity("password Incorrect", HttpStatus.EXPECTATION_FAILED);
			}
		} else {
			return new ResponseEntity("Invalid user email", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/updateUser") // upadte user by getting full body by /getuserdetialsbyemail" api
	public ResponseEntity<?> update(@RequestBody login log) {
		if (log != null) {
			ser.update(log);
			return new ResponseEntity("user updated", HttpStatus.OK);
		} else {
			return new ResponseEntity("user is null", HttpStatus.NO_CONTENT);
		}
	}
@DeleteMapping("/deleteUser")
	public ResponseEntity<?> delete(@RequestParam("uemail") String ue)
	{
	ser.deleteser(ue);
		return new ResponseEntity("user deleted",HttpStatus.OK);
	}

@GetMapping("/getalldoctord")
public ResponseEntity<?> getdoctor()
{
	List<user> list = ser.getalldoctor();
	return new ResponseEntity(list,HttpStatus.OK);
}

@GetMapping("/getalluserreport")
public ResponseEntity<?> userreport()
{ String formate="pdf";
	ser.getalluser(formate);
	return new ResponseEntity("Report generated",HttpStatus.OK);
}

}