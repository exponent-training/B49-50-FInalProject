package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.patient;

@Repository
public interface patientRepository extends JpaRepository<patient, Integer>{

	
	
}
