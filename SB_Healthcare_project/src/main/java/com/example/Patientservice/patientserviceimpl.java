package com.example.Patientservice;

import java.util.List;

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

	@Override
	public patient getpatient(String email) {
		patient pp=pr.findByEmail(email);
		return pp;
	}

	@Override
	public List<patient> getallpatient() {
		List<patient>	lp=pr.findAll();
		return lp;
	}

	@Override
	public int deleteallpatient(List<patient> pp) {
		pr.deleteAll(pp);
		return 1;
	}

	@Override
	public int deletepatientbyemail(patient pp) {
		        pr.delete(pp);
		return 1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
