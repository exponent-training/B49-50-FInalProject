package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
    @GetMapping("/getpatient/{email}")
	   public ResponseEntity<?> getPatient(@PathVariable String email)
	   {
			patient pp=psi.getpatient(email);
			if(pp!=null) {
				return new ResponseEntity<patient>(pp,HttpStatus.CREATED);
			}else {
				ResponseDTO dto=new ResponseDTO();
				dto.setMsg("email does not Exist");
				return new ResponseEntity<ResponseDTO>(dto,HttpStatus.CREATED);
				
			}
			
	   }
    @GetMapping("/getAllpatient")
	   public ResponseEntity<?> getAllPatient()
	   {
			List<patient> pp=psi.getallpatient();
			if(pp!=null) {
				return new ResponseEntity<List<patient>>(pp,HttpStatus.CREATED);
			}else {
				ResponseDTO dto=new ResponseDTO();
				dto.setMsg("patient does not Exist");
				return new ResponseEntity<ResponseDTO>(dto,HttpStatus.CREATED);
				
			}
			
	   }
    @DeleteMapping("/deleteAllpatient")
	   public ResponseEntity<?> deleteAllPatient()
	   {
			List<patient> pp=psi.getallpatient();
			if(pp!=null) {
				int i=psi.deleteallpatient(pp);
				if(i==1) {
					ResponseDTO dto=new ResponseDTO();
					dto.setMsg("all patient successfully deleted");
					return new ResponseEntity<ResponseDTO>(dto,HttpStatus.OK);
					
				}else {
					ResponseDTO dto=new ResponseDTO();
					dto.setMsg("patient can not delete");
					return new ResponseEntity<ResponseDTO>(dto,HttpStatus.BAD_REQUEST);
					
				}
				
			}else {
				ResponseDTO dto=new ResponseDTO();
				dto.setMsg("patient does not Exist");
				return new ResponseEntity<ResponseDTO>(dto,HttpStatus.BAD_REQUEST);
				
			}
			
	   }
    @DeleteMapping("/deletepatient{email}")
	   public ResponseEntity<?> deletePatientbyemail(@PathVariable String email)
	   {
			patient pp=psi.getpatient(email);
			if(pp!=null) {
				int i=psi.deletepatientbyemail(pp);
				if(i==1) {
					ResponseDTO dto=new ResponseDTO();
					dto.setMsg("all patient successfully deleted");
					return new ResponseEntity<ResponseDTO>(dto,HttpStatus.OK);
					
				}else {
					ResponseDTO dto=new ResponseDTO();
					dto.setMsg("patient can not delete");
					return new ResponseEntity<ResponseDTO>(dto,HttpStatus.BAD_REQUEST);
					
				}
				
			}else {
				ResponseDTO dto=new ResponseDTO();
				dto.setMsg("patient does not Exist");
				return new ResponseEntity<ResponseDTO>(dto,HttpStatus.BAD_REQUEST);
				
			}
			
	   }
	
	
	
	
	
	
	
	
	
	
	
	
}
