package com.Service;

import java.util.List;

public interface AdminService {

	void assignRolesInService(String email, String roleName);

	List<String> getAllEmailsFromService();

	List<String> getAllRolesFromService();

}
