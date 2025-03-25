package com.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{


	public List<User> findByRoleRolename(String patient);
}
