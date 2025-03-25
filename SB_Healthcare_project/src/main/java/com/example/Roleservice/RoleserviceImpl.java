package com.example.Roleservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Roles;
import com.example.repository.RoleRepository;

@Service
public class RoleserviceImpl implements RoleServiceInterface{

	@Autowired
	private RoleRepository rr;
	@Override
	public Roles AddRoles(Roles role) {
	Roles roles=rr.save(role);
		return roles;
	}
	@Override
	public Roles findbyrolename(String rolename) {
		Roles role=rr.findByRolename(rolename);
		return role;
	}
	@Override
	public List<Roles> findRoles() {
		List<Roles> role=rr.findAll();
		return role;
	}
	@Override
	public void deletebyrolename(Roles roles) {
		rr.delete(roles);
		
	}
	@Override
	public void deleteallrole(List<Roles> roles) {
		rr.deleteAll(roles);
		
	}
	@Override
	public Roles updaterole(Roles role) {
		Roles r=rr.save(role);
		return r;
	}
	

}
