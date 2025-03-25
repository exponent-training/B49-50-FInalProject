package com.example.serviceIMPL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.Entity.Patient;
import com.example.Repo.PatientRepo;
import com.example.service.PatientService;

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
public class PatientServiceIMPL implements PatientService {

	@Autowired
	private PatientRepo pr;

	@Override
	public void registerPatient(Patient patient) {

		pr.save(patient);

		System.out.println("Patient saved");

	}

	@Override
	
	public void generateReport(String format) throws JRException {

		List<Patient> allPatient = pr.findAll();

		try {
			// 1 load file
			File file = ResourceUtils.getFile("classpath:patient.jrxml");

			// compile report
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

			// datasource creation -> data -> report
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(allPatient);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("created by", "exponent");

			// filling data inside report.                          patient.jrxml             allpatient 
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

			// checking format of report
			if (format.equalsIgnoreCase("html")) {
				JasperExportManager.exportReportToHtmlFile(jasperPrint, "D:\\MyHospitalReports\\patient.html");
			} else if (format.equalsIgnoreCase("pdf")) {
				JasperExportManager.exportReportToPdfFile(jasperPrint, "D:\\MyHospitalReports\\patient.pdf");
			} else if (format.equalsIgnoreCase("csv")) {

				JRCsvExporter csvfile = new JRCsvExporter();

				csvfile.setExporterInput(new SimpleExporterInput(jasperPrint));

				csvfile.setExporterOutput(new SimpleWriterExporterOutput("D:\\MyHospitalReports\\patient.csv"));

				csvfile.exportReport();

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
