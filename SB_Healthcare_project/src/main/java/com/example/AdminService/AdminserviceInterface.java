package com.example.AdminService;

import java.util.List;

import com.example.Entity.Roles;
import com.example.Entity.User;

public interface AdminserviceInterface {

	List<String> getroles();

	List<String> findallEmails();

	List<User> getallUsers();

}
