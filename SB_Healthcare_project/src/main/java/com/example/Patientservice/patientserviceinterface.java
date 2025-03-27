package com.example.Patientservice;

import java.util.List;

import com.example.Entity.patient;

public interface patientserviceinterface {

	patient addpatient(patient p);

	patient getpatient(String email);

	List<patient> getallpatient();

	int deleteallpatient(List<patient> pp);

	int deletepatientbyemail(patient pp);

}
