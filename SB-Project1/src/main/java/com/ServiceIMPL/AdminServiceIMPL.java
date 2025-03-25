package com.ServiceIMPL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.Controller.AdminController;
import com.Entity.Login;
import com.Entity.Roles;
import com.Entity.User;
import com.Repository.LoginRepository;
import com.Repository.RolesRepository;
import com.Repository.UserRepository;
import com.Service.AdminService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

@Service
public class AdminServiceIMPL implements AdminService {

	@Autowired
	private LoginRepository lr;

	@Autowired
	private RolesRepository rr;

	@Autowired
	private UserRepository ur;

	Logger log = LoggerFactory.getLogger(AdminController.class);

	@Override
	public int assignRoleInService(String email, String rolename) {

		Login login = lr.findByEmail(email);

		if (login != null && login.getUser() != null) {

			Roles roles = rr.findByRolename(rolename);

			if (roles != null) {

				if (!(login.getUser().getRole().getRolename().equalsIgnoreCase(rolename))) {
					login.getUser().setRole(roles);
					lr.save(login);

					log.info("Role Assigned!!");
					return 1;
				} else {
					log.warn("Role already assigned!!");
					return 2;
				}

			} else {
				log.warn("Role Doesn't Exsit!!");
				return 3;

			}

		} else {
			log.warn("Wrong Email!!");
			return 0;
		}

	}

	@Override
	public int getAdminInService(String email, String password) {
		Login admin = lr.findByEmailAndPassword(email, password);

		if (admin != null) {
			log.info("You can update Role!!");
			return 1;

		} else {
			log.warn("You are not Admin!!");
			return 0;

		}
	}

	@Override
	public int updateRoleInService(String email, String rolename) {
		Login login = lr.findByEmail(email);

		if (login != null && login.getUser() != null) {

			Roles roles = rr.findByRolename(rolename);

			if (roles != null) {

				if (!(login.getUser().getRole().getRolename().equalsIgnoreCase(rolename))) {
					login.getUser().setRole(roles);
					lr.save(login);

					log.info("Role updated!!");
					return 1;
				} else {
					log.warn("Role already Same!!");
					return 2;
				}

			} else {
				log.warn("Role Doesn't Exsit!!");
				return 3;

			}

		} else {
			log.warn("Wrong Email!!");
			return 0;
		}

	}

	@Override
	public List<String> getAllEmailInService() {

		List<String> list = lr.getEmailOnly();

		return list;
	}

	@Override
	public List<String> getAllRolesInService() {

		List<String> list = rr.getRoles();

		return list;
	}

	@Override
	public void getReportInService(String format) throws JRException {

		List<User> allUser = ur.findAll();

		try {
			// 1 load file
			File file = ResourceUtils.getFile("classpath:UserDetailes.jrxml");

			// compile report
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

			// datasource creation -> data -> report
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(allUser);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("created by", "exponent");

			// filling data inside report. patient.jrxml allpatient
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

			// checking format of report
			if (format.equalsIgnoreCase("html")) {
				JasperExportManager.exportReportToHtmlFile(jasperPrint, "E:\\My Spring Boot\\userReport.html");
			} else if (format.equalsIgnoreCase("pdf")) {
				JasperExportManager.exportReportToPdfFile(jasperPrint, "E:\\My Spring Boot\\userReport.pdf");
			} else if (format.equalsIgnoreCase("csv")) {

				JRCsvExporter csvfile = new JRCsvExporter();

				csvfile.setExporterInput(new SimpleExporterInput(jasperPrint));

				csvfile.setExporterOutput(new SimpleWriterExporterOutput("E:\\My Spring Boot\\userReport.csv"));

				csvfile.exportReport();

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void getPatientReportInService(String format) throws JRException {

		List<User> patients = ur.findByRoleRolename("Patient");

		try {
			// 1 load file
			File file = ResourceUtils.getFile("classpath:PatientDetailes.jrxml");

			// compile report
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

			// datasource creation -> data -> report
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(patients);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("created by", "exponent");

			// filling data inside report. patient.jrxml allpatient
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

			// checking format of report
			if (format.equalsIgnoreCase("html")) {
				JasperExportManager.exportReportToHtmlFile(jasperPrint,
						"E:\\My Spring Boot\\Report Generartion\\PatientReport.html");
			} else if (format.equalsIgnoreCase("pdf")) {
				JasperExportManager.exportReportToPdfFile(jasperPrint,
						"E:\\My Spring Boot\\Report Generartion\\PatientReport.pdf");
			} else if (format.equalsIgnoreCase("csv")) {

				JRCsvExporter csvfile = new JRCsvExporter();

				csvfile.setExporterInput(new SimpleExporterInput(jasperPrint));

				csvfile.setExporterOutput(
						new SimpleWriterExporterOutput("E:\\My Spring Boot\\Report Generartion\\PatientReport.csv"));

				csvfile.exportReport();

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
