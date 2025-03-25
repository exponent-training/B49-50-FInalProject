package com.Service;

import com.Entity.Patient;

import net.sf.jasperreports.engine.JRException;

public interface PatientService {

	public void registerPatient(Patient patient);

	public void generateReport(String format) throws JRException;

}
