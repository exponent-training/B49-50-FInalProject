package com.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Entity.Login;
import com.Entity.Roles;
import com.Entity.User;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {

	public Roles findByRoleName(String roleName);

	@Query(value = "select roleName from Roles")
	public List<String> getAllRoles();

	public void deleteByRoleName(String roles);

//	void deleteByUser(User user);


}
