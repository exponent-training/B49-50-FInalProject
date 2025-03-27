package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.ResponseDTO;
import com.example.Entity.patient;
import com.example.Patientservice.patientserviceinterface;

@RestController
@RequestMapping("/exponent/patient")
public class PatientController {

	
	
	@Autowired
	private patientserviceinterface psi;
	
	
	
	@PostMapping("/addpatient")
   public ResponseEntity<?> addPatient(@RequestBody patient p)
   {
		patient pp=psi.addpatient(p);
		if(pp!=null) {
			return new ResponseEntity<patient>(pp,HttpStatus.CREATED);
		}else {
			ResponseDTO dto=new ResponseDTO();
			dto.setMsg("patient not added");
			return new ResponseEntity<ResponseDTO>(dto,HttpStatus.CREATED);
			
		}
		
   }
	
	
	
	
	
	
	
	
	
	
	
	
	
}
