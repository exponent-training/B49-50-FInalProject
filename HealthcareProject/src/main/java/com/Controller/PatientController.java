package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.Patient;
import com.Service.PatientService;

import net.sf.jasperreports.engine.JRException;

@RestController
public class PatientController {

	@Autowired
	private PatientService ps;

	@PostMapping("registerPatient")
	public ResponseEntity<?> registerPatient(@RequestBody Patient patient) {

		ps.registerPatient(patient);

		return new ResponseEntity("Patient added", HttpStatus.CREATED);

	}

	@GetMapping("/generateReport/{format}")
	public ResponseEntity<?> generateReport(@PathVariable String format) throws JRException {

		ps.generateReport(format);

		return new ResponseEntity("Report created", HttpStatus.CREATED);

	}
}
