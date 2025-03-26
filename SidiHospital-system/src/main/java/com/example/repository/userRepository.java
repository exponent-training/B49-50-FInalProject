 package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.User;
@Repository
public interface userRepository  extends JpaRepository<User,Integer>{

	
	


	
}
