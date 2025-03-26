package com.example.project1.repository;

import com.example.project1.entity.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginDetailsRepository extends JpaRepository<LoginDetails,Integer> {
	List<LoginDetails> findByEmail(String email) ;

	LoginDetails getLoginDetailsByEmail(String email);

	@Query("SELECT l.email FROM LoginDetails l")
	List<String> getAllEmailsOnly();

}
