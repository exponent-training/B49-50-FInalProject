package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.Login;

@Repository
public interface ILoginRepository extends JpaRepository<Login, Integer> {

	public Login findByEmail(String email);

	public Login findByEmailAndPassword(String email, String password);
}
