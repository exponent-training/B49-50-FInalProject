package com.example.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Entity.Login;

@Repository
public interface LoginRepo extends JpaRepository<Login, Integer> {

	public Login findByEmail(String email);

	public Login findByEmailAndPassword(String email, String password);

	@Query(value = "select email from Login")
	public List<String> getallEmailsOnly();

}
