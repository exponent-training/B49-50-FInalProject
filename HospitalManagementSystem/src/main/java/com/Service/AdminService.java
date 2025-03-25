package com.Service;

import java.util.List;

import net.sf.jasperreports.engine.JRException;

public interface AdminService {

	void assignedRoleInService(String email, String roleName);

	List<String> getallEmailInService();

	List<String> getallRolesInService();

	void generateAllUserReportInservice(String format) throws JRException;

	void generatePatientsReportInservice(String format) throws JRException;

}
