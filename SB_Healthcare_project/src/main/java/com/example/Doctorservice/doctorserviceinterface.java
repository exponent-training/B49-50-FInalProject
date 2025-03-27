package com.example.Doctorservice;

import java.util.List;

import com.example.Entity.Doctor;

public interface doctorserviceinterface {

	List<Doctor> getAlldoctor();

	Doctor getdoctor(String email);

}
