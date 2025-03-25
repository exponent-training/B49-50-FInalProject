package com.ServiceIMPL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Controller.RolesController;
import com.Entity.Roles;
import com.Repository.RolesRepository;
import com.Service.RolesService;

@Service
public class RolesServiceIMPL implements RolesService {

	@Autowired
	private RolesRepository rr;
	
	Logger logger=LoggerFactory.getLogger(RolesController.class);

	@Override
	public int addRolesInSerivce(Roles roles) {
		
		Roles role=rr.findByRolename(roles.getRolename());
		
		if(role!=null) {
			logger.warn("This Role is alread exist!!");
			return 0;
		}else {
			rr.save(roles);
			logger.info("Role added!!");
			return 1;
		}
	}
}
