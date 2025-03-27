package com.example.Doctorservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Doctor;
import com.example.repository.DoctorRepository;

@Service
public class doctorserviceImpl implements doctorserviceinterface {

	@Autowired
	private DoctorRepository dr;

	@Override
	public List<Doctor> getAlldoctor() {
		List<Doctor> doctor=dr.findAll();
		return doctor;
	}

	@Override
	public Doctor getdoctor(String email) {
		Doctor d=dr.findByEmail(email);
		return d;
	}
}
