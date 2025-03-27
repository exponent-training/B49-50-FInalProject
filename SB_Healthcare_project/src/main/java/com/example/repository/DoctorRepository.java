package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Integer>{

	Doctor findByEmail(String email);

	Doctor findByDid(int did);

}
