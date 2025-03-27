package com.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Controller.DoctorController;
import com.Entity.Doctor;
import com.Entity.User;
import com.Repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService{
	
	Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);
	
	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public ResponseEntity<?> RegisterDoctorInService(Doctor doctor) {
		if (doctor == null || doctor.getDName() == null || doctor.getSpecializist() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Doctor saveDoctor = doctorRepository.save(doctor);
        
        return new ResponseEntity<>(saveDoctor, HttpStatus.CREATED);
	
		
	}
	
	@Override
	public ResponseEntity<?> loginDoctorInService(Doctor doctor) {
		if(doctor != null) {
			List<Doctor> result =  doctorRepository.findAll();
		  logger.info("login successfully ");
		  return  new ResponseEntity(result, HttpStatus.OK);
		  }else {
		    logger.info("wrong id, please enter correct id");
		    
		    } 
		return  new ResponseEntity("please enter Correct Id ", HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<?> getdoctorInService(Doctor doctor) {
		
		List<Doctor> allDoctors =  doctorRepository.findAll();
		return  new ResponseEntity(allDoctors, HttpStatus.OK);
	}

	@Override
	public int UpdateDoctorById(Doctor doctor) {
		  if(doctor != null) {
			  doctorRepository.save(doctor);
		  logger.info("Doctor Updated ");
		  }else {
		    logger.info("Doctor should not be null");
		    return 0;
		    } 
		  return 1;
		
	}

	@Override
	public ResponseEntity<?> deleteDoctorById(int id) {
		if(doctorRepository.existsById(id)){
			logger.info("Deleted Doctor with id {} from repository", id);
			doctorRepository.deleteById(id);
			return new ResponseEntity<>("Doctor with id " + id + " deleted", HttpStatus.OK);
		}else {
			logger.error("Deleted role or id does not exist");
			return new ResponseEntity<>("Role with id " + id + " does not exist", HttpStatus.BAD_REQUEST);
		}
	
		
	}

}
