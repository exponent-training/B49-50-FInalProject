package com.Service;

import com.DTO.DoctorDTO;
import com.Entity.Doctor;

public interface DoctorService {

	public void registerDoctorInService(Doctor doctor);

	public Doctor getDoctorByIdInService(int id);

	public Doctor loginDoctorInService(int id);

	public Doctor updateDoctorDetail(int id, Doctor doctor);

}
