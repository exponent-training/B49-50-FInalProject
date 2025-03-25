package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.Login;

@Repository
public interface LoginRepo extends JpaRepository<Login,Integer>{
	
//	Login findByEmail(String email);
	
	Login findByEmailAndPassword(String email,String password);

}
