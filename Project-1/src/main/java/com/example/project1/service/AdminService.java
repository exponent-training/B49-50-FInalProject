package com.example.project1.service;

import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;


public interface AdminService {
	ResponseEntity<?> getAllEmailsInService();

	ResponseEntity<?> getAllRolesInService();

	void getReportInService(String format) throws JRException;
}
