package com.example.Repository;

import java.util.List;

import org.apache.catalina.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Entity.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {

	public Roles findByRoleName(String string);

	//public Role findByRoleName(String roleName);
	
	@Query(value = "select roleName from Roles")
	public List<String> getAllRoles();
}
