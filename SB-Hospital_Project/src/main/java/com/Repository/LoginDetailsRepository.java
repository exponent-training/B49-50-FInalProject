package com.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Entity.LoginDetails;
import com.Entity.User;

public interface LoginDetailsRepository extends JpaRepository<LoginDetails,Integer> {
	
	

	LoginDetails getLoginDetailsByEmail(String email);

	void save(List<LoginDetails> loginDetails);

	List<LoginDetails> findByEmail(String email);

	LoginDetails getByEmail(String email);

	@Query(value = "select email from LoginDetails")
	public List<String> getallEmailsOnly();
}
