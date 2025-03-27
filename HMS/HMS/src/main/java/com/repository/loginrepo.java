package com.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.login;
import com.Entity.user;
import java.lang.String;
import java.util.List;
import java.util.Optional;

@Repository
public interface loginrepo extends JpaRepository<login, Integer> {
	
	login findByUemail(String uemail);
	
	

}
