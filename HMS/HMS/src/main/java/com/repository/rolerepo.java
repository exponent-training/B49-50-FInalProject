package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.roles;
import java.lang.String;
import java.util.List;
@Repository
public interface rolerepo extends JpaRepository<roles, Integer>{
	
	roles findByRoleName(String roleName);
}
