package com.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Entity.User;
import com.Entity.UserLogin;

@Repository
public interface LoginRepository extends JpaRepository<UserLogin, Integer> {
	public Optional<UserLogin> findByemail(String email);

	public UserLogin findByEmailAndPassword(String email, String password);

	@Query(value = "select email from UserLogin")
	public List<String> getAllEmailsOnly();
}
