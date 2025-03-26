package com.example.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.Entity.Roles;
import com.example.Entity.User;
import com.example.repository.LoginRepository;
import com.example.repository.RoleRepository;
import com.example.repository.userRepository;

@Service
public class AdminserviceImpl implements AdminserviceInterface {

	@Autowired 
	private RoleRepository rr;
	@Autowired
	private LoginRepository lr;
	@Autowired
	private userRepository ur;
	@Override
	public List<String> getroles() {
		
		List<String> rolestring=rr.findonlyroles();
		return rolestring;
	}

	@Override
	public List<String> findallEmails() {
		List<String> list=lr.findAllEmails();
		return list;
	}

	@Override
	public List<User> getallUsers() {
		List<User> users=ur.findAll();
		return users;
	}
 

	
	
}
