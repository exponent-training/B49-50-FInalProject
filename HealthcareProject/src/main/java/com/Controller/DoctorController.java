package com.Controller;

import javax.print.Doc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.DoctorDTO;
import com.DTO.DoctorLoginDTO;
import com.Entity.Doctor;
import com.Service.DoctorService;

@RestController
public class DoctorController {

	Logger logger = LoggerFactory.getLogger(DoctorController.class);

	@Autowired
	private DoctorService ds;

	@GetMapping("/getDoctorById/{id}")
	public ResponseEntity<?> getDoctorById(@PathVariable int id) {

		Doctor doctor = ds.getDoctorByIdInService(id);

		DoctorDTO dto = new DoctorDTO();

		dto.setDid(doctor.getDid());
		dto.setEmail(doctor.getEmail());
		dto.setExperience(doctor.getExperience());
		dto.setGender(doctor.getGender());
		dto.setSchedule(doctor.getSchedule());
		dto.setSpecialization(doctor.getSpecialization());

		logger.info("Doctor data: " + dto);

		if (doctor != null) {
			return new ResponseEntity(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity("Doctor is null", HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@GetMapping("/loginDoctor/{id}")
	public ResponseEntity<?> loginDoctor(@PathVariable int id) {

		Doctor doctor = ds.loginDoctorInService(id);

		DoctorLoginDTO dldto = new DoctorLoginDTO();

		dldto.setDid(doctor.getDid());
		dldto.setEmail(doctor.getEmail());
		dldto.setSchedule(doctor.getSchedule());

		logger.info("Login details: " + dldto);

		if (doctor != null) {
			return new ResponseEntity(dldto, HttpStatus.OK);
		} else {
			return new ResponseEntity("Doctor is null", HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@PutMapping("/updateDoctorDetail/{id}")
	public ResponseEntity<Doctor> updateDoctorDetail(@PathVariable int id, @RequestBody Doctor doctor) {

		Doctor updatedDoctor = ds.updateDoctorDetail(id, doctor);

		return new ResponseEntity("Doctor updated", HttpStatus.OK);

	}

}
