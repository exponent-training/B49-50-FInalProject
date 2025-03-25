package com.ServiceIMPL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.Controller.RoleController;
import com.Entity.Roles;
import com.Entity.User;
import com.Entity.UserLogin;
import com.Repository.LoginRepository;
import com.Repository.RoleRepository;
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
	Logger logger = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private LoginRepository lr;

	@Autowired
	private RoleRepository rr;

	@Autowired
	private UserRepository ur;

	@Override
	public void assignedRoleInService(String email, String roleName) {

		UserLogin login = lr.findByemail(email).get();

		if (login != null && login.getUser() != null) {

			Roles role = rr.findByRoleName(roleName);
			if (role != null) {

				if (!login.getUser().getRole().getRoleName().equalsIgnoreCase(roleName)) {

					User user = login.getUser();
					user.setRole(role);
					ur.save(user);
					logger.info("role assigned");

				} else {
					logger.warn("role already assigned");
				}

			} else {
				logger.warn("role does not exist");
			}

		} else {
			logger.warn("wrong email");
		}

	}

	@Override
	public List<String> getallEmailInService() {
		List<String> allEmail = lr.getAllEmailsOnly();
		System.out.println(allEmail);
		return allEmail;
	}

	@Override
	public List<String> getallRolesInService() {
		List<String> allRoles = rr.getAllRoles();
		return allRoles;
	}

	@Override
	public void generateAllUserReportInservice(String format) throws JRException {
		List<User> alluser = ur.findAll();

		try {
			// 1 load file
			File file = ResourceUtils.getFile("classpath:user.jrxml");

			// compile report
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

			// datasource creation -> data -> report
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(alluser);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("created by", "admin@saisawali");

			// filling data inside report. patient.jrxml allpatient
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

			// checking format of report
			if (format.equalsIgnoreCase("html")) {
				JasperExportManager.exportReportToHtmlFile(jasperPrint, "D:\\hospitalReport\\user.html");
			} else if (format.equalsIgnoreCase("pdf")) {
				JasperExportManager.exportReportToPdfFile(jasperPrint, "D:\\hospitalReport\\user.pdf");
			} else if (format.equalsIgnoreCase("csv")) {

				JRCsvExporter csvfile = new JRCsvExporter();

				csvfile.setExporterInput(new SimpleExporterInput(jasperPrint));

				csvfile.setExporterOutput(new SimpleWriterExporterOutput("D:\\hospitalReport\\user.csv"));

				csvfile.exportReport();

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void generatePatientsReportInservice(String format) throws JRException {

		List<User> AllPatients = ur.findByRole_Rid(9);
		System.out.println(AllPatients);

		try {
			// 1 load file
			File file = ResourceUtils.getFile("classpath:AllPatients.jrxml");

			// compile report
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

			// datasource creation -> data -> report
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(AllPatients);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("created by", "admin@saisawali");

			// filling data inside report. patient.jrxml allpatient
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

			// checking format of report
			if (format.equalsIgnoreCase("html")) {
				JasperExportManager.exportReportToHtmlFile(jasperPrint, "D:\\hospitalReport\\Patients.html");
			} else if (format.equalsIgnoreCase("pdf")) {
				JasperExportManager.exportReportToPdfFile(jasperPrint, "D:\\hospitalReport\\Patients.pdf");
			} else if (format.equalsIgnoreCase("csv")) {

				JRCsvExporter csvfile = new JRCsvExporter();

				csvfile.setExporterInput(new SimpleExporterInput(jasperPrint));

				csvfile.setExporterOutput(new SimpleWriterExporterOutput("D:\\hospitalReport\\Patients.csv"));

				csvfile.exportReport();

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
