package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Entity.Login;
@Repository
public interface LoginRepository extends JpaRepository<Login,Integer>

{

	Login findByEmail(String email);

	Login findByEmailAndPassword(String email,String passsword);

	void deleteByEmail(String email);

	  @Query(value = "select email from Login ")
		List<String> findAllEmails();
	


	

	

	

}
