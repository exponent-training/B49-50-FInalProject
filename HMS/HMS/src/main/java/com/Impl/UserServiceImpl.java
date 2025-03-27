package com.Impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.aspectj.weaver.GeneratedReferenceTypeDelegate;
import org.hibernate.engine.jdbc.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.Entity.login;
import com.Entity.roles;
import com.Entity.user;
import com.repository.loginrepo;
import com.repository.rolerepo;
import com.repository.userrepo;
import com.service.UserServices;
import com.utility.GenerateRandomNumber;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class UserServiceImpl implements UserServices {
	@Autowired
	private loginrepo lr;
	@Autowired
	private rolerepo rr;
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private userrepo ur;

	@Override
	public ResponseEntity<?> adduser(login log) {// to register new user defualt Normal User
		if (!isValidEmail(log.getUemail())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email address");
		} else {
			login existingUser = lr.findByUemail(log.getUemail());
			if (existingUser != null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email address already exists");
			}
		}
		log.getUsers().setUnum(GenerateRandomNumber.generatenum());

		log.getUsers().setLog(log);
		if (log.getUsers() == null) {
			throw new RuntimeException("Users object is null in log");
		}

		System.out.println("hello");
		log.getUsers().setUrole(rr.findById(3).orElse(null));
		lr.save(log);
		return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
	}

	private boolean isValidEmail(String email) {
		return email != null && email.matches("^[A-Za-z0-9._%+-]+@gmail.com$");
	}

	@Override
	public void updatepass(login loginuser, String newup) {
		loginuser.setUpass(newup);
		lr.save(loginuser);
	}

	@Override
	public void update(login log) {
		user users = log.getUsers();
		ur.save(users);

	}

	@Override
	public void deleteser(String ue) {
		login userdel = lr.findByUemail(ue);
		lr.delete(userdel);
		logger.info("User deleted successfully");
	}

	@Override
	public List<user> getalldoctor() {
	List<user> list = ur.findByUroleRoleName("doctor");	
	return list;
	}

	@Override
	public ResponseEntity<?> getalluser(String format) {
		List<user> findAll = ur.findAll();
		
		try {
		if (findAll!=null) {
			     // Load and compile the Jasper report
		        InputStream reportStream = new ClassPathResource("C:\\Users\\Admin\\Documents\\workspace-sts-3.9.12.RELEASE\\HMS\\src\\main\\resources\\pateint.jrxml").getInputStream();
		        JasperReport jasperReport;
				
					jasperReport = JasperCompileManager.compileReport(reportStream);
				
		        // Create a data source
		        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(findAll);

		        // Set parameters
		        Map<String, Object> parameters = new HashMap<>();
		        parameters.put("createdBy", "Exponent");

		        // Fill the report
		        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		        // Export report to chosen format
		        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		        switch (format.toLowerCase()) {
		            case "pdf":
		                JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
		                break;
		            case "html":
		                JasperExportManager.exportReportToHtmlFile(jasperPrint, "reports/patient.html");
		               // return StreamUtils.copyToByteArray(new ClassPathResource("reports/patient.html").getInputStream());
		            default:
		                throw new IllegalArgumentException("Invalid format: " + format);
		        }

		      //  return byteArrayOutputStream.toByteArray();

			return new ResponseEntity("report generated",HttpStatus.OK);
		}
		else {
			return new ResponseEntity("no data fetch",HttpStatus.NO_CONTENT);
		}
		} catch (JRException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}}
