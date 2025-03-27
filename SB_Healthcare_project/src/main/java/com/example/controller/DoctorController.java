package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Doctorservice.doctorserviceinterface;
import com.example.Entity.Doctor;
import com.example.Entity.ResponseDTO;

@RestController
@RequestMapping("/exponent/Doctor")
public class DoctorController {

	Logger logger=LoggerFactory.getLogger(DoctorController.class);
	
	@Autowired
	private doctorserviceinterface dsi;
	
	@GetMapping("/getalldoctor")
	public ResponseEntity<?> getAllDoctor()
	{
		List<Doctor> doctor=dsi.getAlldoctor();
		if(doctor!=null) {
			logger.info("Doctors :"+doctor);
			return new ResponseEntity<List<Doctor>>(doctor,HttpStatus.OK);
		}else {
			ResponseDTO dto =new ResponseDTO();
			dto.setMsg("doctor can not available");
			return new ResponseEntity<ResponseDTO>(dto,HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/getdoctor/{email}")
	public ResponseEntity<?> getDoctor(@PathVariable String email)
	{
		Doctor  doctor=dsi.getdoctor(email);
		if(doctor!=null) {
			logger.info("Doctors :"+doctor);
			return new ResponseEntity<Doctor>(doctor,HttpStatus.OK);
		}else {
			ResponseDTO dto =new ResponseDTO();
			dto.setMsg("doctor email is not exist");
			return new ResponseEntity<ResponseDTO>(dto,HttpStatus.OK);
		}
	}
	
}
