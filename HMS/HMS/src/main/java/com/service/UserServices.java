package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.Entity.login;
import com.Entity.user;

public interface UserServices {

	public ResponseEntity<?> adduser(login log);

	public void updatepass(login loginuser, String newup);

	public void update(login log);

	public void deleteser(String ue);

	public List<user> getalldoctor();

	public ResponseEntity<?> getalluser(String formate);

}
