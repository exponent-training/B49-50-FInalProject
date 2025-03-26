package com.example.project1.serviceImpl;

import com.example.project1.entity.User;
import com.example.project1.repository.LoginDetailsRepository;
import com.example.project1.repository.RoleRepository;
import com.example.project1.repository.UserRepository;
import com.example.project1.service.AdminService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private LoginDetailsRepository lDRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	UserRepository userRepo;

	@Override
	public ResponseEntity<?> getAllEmailsInService() {
		return new ResponseEntity<>( lDRepo.getAllEmailsOnly(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAllRolesInService() {
		return new ResponseEntity<>(roleRepo.getAllRoleNames(),HttpStatus.OK);
	}

	@Override
	public void getReportInService(String format) throws JRException {
		List<User> all = userRepo.findAll();
		try {
			File file = ResourceUtils.getFile("classpath:patient.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(all);
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("created by", "exponent");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);


			if (format.equalsIgnoreCase("html")) {
				JasperExportManager.exportReportToHtmlFile(jasperPrint,"C:\\Users\\AMD\\Desktop\\Reports\\patient.html");
			} else if (format.equalsIgnoreCase("pdf")) {
				JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\AMD\\Desktop\\Reports\\patient.pdf");
			} else if (format.equalsIgnoreCase("csv")) {

				JRCsvExporter csvfile = new JRCsvExporter();

				csvfile.setExporterInput(new SimpleExporterInput(jasperPrint));

				csvfile.setExporterOutput(new SimpleWriterExporterOutput("C:\\Users\\AMD\\Desktop\\Reports\\patient.csv"));

				csvfile.exportReport();

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
