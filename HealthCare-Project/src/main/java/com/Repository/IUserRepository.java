package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
//	User findByEmail(String email);
}
