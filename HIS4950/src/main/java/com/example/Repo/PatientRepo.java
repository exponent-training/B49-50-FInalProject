package com.example.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {

}
