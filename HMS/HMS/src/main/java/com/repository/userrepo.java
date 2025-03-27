package com.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.user;
import com.Entity.roles;
import java.util.List;


@Repository
public interface userrepo extends JpaRepository<user, Integer> {

	//@Query("SELECT u FROM User u WHERE u.role = :role")
    //List<User> findByUserRoleName(@Param("roleName") String role);	

	 List<user> findByUroleRoleName(String roleName); 
}
