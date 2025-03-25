package com.Service;

import java.util.List;

import net.sf.jasperreports.engine.JRException;

public interface AdminService {

	int assignRoleInService(String email, String rolename);

	List<String> getAllEmailInService();

	List<String> getAllRolesInService();

	void getReportInService(String format) throws JRException;

	void getPatientReportInService(String format) throws JRException;

	int getAdminInService(String email,String password);

	int updateRoleInService(String email, String rolename);

}
