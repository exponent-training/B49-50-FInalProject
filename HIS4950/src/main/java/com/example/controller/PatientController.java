package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Patient;
import com.example.service.PatientService;

import net.sf.jasperreports.engine.JRException;

@RestController
public class PatientController {

	@Autowired
	private PatientService ps;

	@PostMapping("/registerP")
	public ResponseEntity<?> registerPatient(@RequestBody Patient patient) {

		ps.registerPatient(patient);

		return new ResponseEntity("patient added", HttpStatus.CREATED);

	}

	@GetMapping("/generateReport/{format}")
	public ResponseEntity<?> generateReport(@PathVariable String format) throws JRException {
		ps.generateReport(format);

		return new ResponseEntity("report created", HttpStatus.CREATED);
	}

	@Scheduled(fixedRate = 5000)
	public void generateReportForCertainperiod() throws JRException {
		String format = "pdf";
		ps.generateReport(format);
		System.out.println("Report generated for patient" + System.currentTimeMillis());

	}

}
