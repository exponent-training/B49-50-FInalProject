package com.Service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DTO.DoctorDTO;
import com.Entity.Doctor;
import com.Repository.DoctorRepository;

@Service
public class DoctorServiceIMPL implements DoctorService {

	Logger logger = LoggerFactory.getLogger(DoctorServiceIMPL.class);

	@Autowired
	private DoctorRepository dr;

	@Override
	public void registerDoctorInService(Doctor doctor) {

		dr.save(doctor);

		logger.info("Doctor saved");
	}

	@Override
	public Doctor getDoctorByIdInService(int id) {

		Doctor doctor = dr.findById(id).get();

		return doctor;
	}

	@Override
	public Doctor loginDoctorInService(int id) {

		Doctor doctor = dr.findById(id).get();

		return doctor;

	}

	@Override
	public Doctor updateDoctorDetail(int id, Doctor doctor) {

		Optional<Doctor> doctordb = dr.findById(id);

		if (doctordb != null) {

			Doctor existingDoctor = doctordb.get();

			existingDoctor.setExperience(doctor.getExperience());
			existingDoctor.setSpecialization(doctor.getSpecialization());
			existingDoctor.setSchedule(doctor.getSchedule());

			dr.save(existingDoctor);

		} else {
			logger.info("Doctor is null");
		}

		return doctor;

	}

}
