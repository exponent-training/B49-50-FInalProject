package com.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Entity.Login;
import com.Entity.User;

@Repository
public interface LoginRepository extends JpaRepository<Login,Integer>{
	
	public Login findByEmail(String email);
		
	public Login findByEmailAndPassword(String email, String password);
	
	public Login findByOtp(String otp);
	
	@Query(value="select email from Login")
	public List<String>getEmailOnly();


}