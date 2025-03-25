package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Entity.Roles;
@Repository
public interface RoleRepository  extends JpaRepository<Roles,  Integer>{

	Roles findByRolename(String rolename);
	
    @Query(value = "select rolename from Roles")
	List<String> findonlyroles();
    

}
