package com.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.apache.bcel.util.ClassPath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.Entity.Patient;
import com.Repository.PatientRepository;

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

	Logger logger = LoggerFactory.getLogger(PatientServiceIMPL.class);

	@Autowired
	private PatientRepository pr;

	@Override
	public void registerPatient(Patient patient) {

		pr.save(patient);

		logger.info("Patient saved");

	}

	@Override
	public void generateReport(String format) throws JRException {

		List<Patient> allPatient = pr.findAll();

		try {

			// 1 load file
			File file = ResourceUtils.getFile("classpath:PatientDetails.jrxml");

			// compile report
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

			// datasource creation -> data -> report
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(allPatient);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("created by", "exponent");

			// filling data inside report. patient.jrxml allpatient
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

			// checking format of report

			if (format.equalsIgnoreCase("html")) {

				JasperExportManager.exportReportToHtmlFile(jasperPrint, "C:\\MyHospitalReports\\patient.html");

			} else if (format.equalsIgnoreCase("pdf")) {

				JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\MyHospitalReports\\patient.pdf");

			} else if (format.equalsIgnoreCase("csv")) {

				JRCsvExporter csvfile = new JRCsvExporter();

				csvfile.setExporterInput(new SimpleExporterInput(jasperPrint));

				csvfile.setExporterOutput(new SimpleWriterExporterOutput("C:\\MyHospitalReports\\patient.csv"));

				csvfile.exportReport();

			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

	}

}
