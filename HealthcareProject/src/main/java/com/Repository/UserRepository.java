package com.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.Roles;
import com.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUserNumber(String userNumber);

	public User findByEmail(String email);

	Optional<User> findUserByEmail(String email);

	public boolean existsByEmail(String email);

	void deleteByEmail(String email);

	List<User> findByRoles_Rid(int rid);

}
