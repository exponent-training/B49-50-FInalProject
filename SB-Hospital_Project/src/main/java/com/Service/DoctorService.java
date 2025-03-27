package com.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Entity.Doctor;
import com.Entity.User;

@Service
public interface DoctorService {

	 ResponseEntity<?> RegisterDoctorInService(Doctor doctor);

	 ResponseEntity loginDoctorInService(Doctor doctor);
	 
	ResponseEntity<?> getdoctorInService(Doctor doctor);

	int UpdateDoctorById(Doctor doctor);

	ResponseEntity<?> deleteDoctorById(int id);

	

}
