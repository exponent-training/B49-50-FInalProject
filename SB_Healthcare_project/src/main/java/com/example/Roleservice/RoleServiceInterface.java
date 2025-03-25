package com.example.Roleservice;

import java.util.List;

import com.example.Entity.Roles;

public interface RoleServiceInterface {

	Roles AddRoles(Roles role);

	Roles findbyrolename(String rolename);

	List<Roles> findRoles();

	void deletebyrolename(Roles roles);

	void deleteallrole(List<Roles> roles);

	Roles updaterole(Roles role);

}
