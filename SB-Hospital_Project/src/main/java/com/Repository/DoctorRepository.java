package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.Entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer>{

	Doctor save(Doctor doctor);

	boolean existsById(int id);

	

	



	

	

	
	

	

}
