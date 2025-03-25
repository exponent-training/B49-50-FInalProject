package com.example.service;

import com.example.Entity.Patient;

import net.sf.jasperreports.engine.JRException;

public interface PatientService {

	void registerPatient(Patient patient);

	void generateReport(String format) throws JRException;

}
