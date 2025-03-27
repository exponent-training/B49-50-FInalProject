package com.example.Patientservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.patient;
import com.example.repository.patientRepository;

@Service
public class patientserviceimpl  implements patientserviceinterface{

	
	@Autowired
	private patientRepository pr;

	@Override
	public patient addpatient(patient p) {
	  patient p1	=pr.save(p);
		return p1;
	}
	
}
