package com.example.project1.repository;

import com.example.project1.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
	Roles findByRoleName(String roleName);

	boolean existsById(int id);

	Roles getRolesByRoleName(String roleName);

	@Query("select r.roleName from Roles r")
	List<String> getAllRoleNames();
}
