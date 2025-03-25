package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

	public Roles findByRolename(String rolename);
	
	@Query(value="select rolename from Roles")
	public List<String>getRoles();
}
