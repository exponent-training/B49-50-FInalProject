package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.Roles;

@Repository
public interface IRoleRepository  extends  JpaRepository<Roles, Integer>{

	public Roles findByRoleName(String roleName);
}
