package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> 
{
	//public User findbyUserNumber(String usernumber);

	//public User findByEmail(String email);

	

}
