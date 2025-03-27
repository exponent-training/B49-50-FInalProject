package com.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.Doctor;
import com.Entity.LoginDetails;
import com.Entity.User;
import com.Repository.DoctorRepository;
import com.Service.DoctorService;

@RestController
public class DoctorController {

	Logger logger = LoggerFactory.getLogger(DoctorController.class);
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	private DoctorRepository dr;
	
	@GetMapping("/loginDoctor")
	public ResponseEntity login(@RequestBody Doctor doctor) {
		return doctorService.loginDoctorInService(doctor);
	}
	
	public ResponseEntity<?> GetAllDoctors(@PathVariable int id, @RequestParam Doctor doctor) {
		return doctorService.getdoctorInService(doctor);
	}
	
	  @PutMapping("/updatDoctor/{did}")
			public ResponseEntity<?> UpdateDoctor(@PathVariable int id, @RequestBody Doctor doctor){
				
				 logger.info("update doctor" ,id , doctor);
			    	int result = doctorService.UpdateDoctorById(doctor);
			    	
					
					  if(result == 0) { return new ResponseEntity("Doctor Updated Successfully...!",
					  HttpStatus.OK); }else 
					  { 
						 return new ResponseEntity("Doctor is null...!",  HttpStatus.NOT_ACCEPTABLE); }
					 	
			    	 
			}

	
}
