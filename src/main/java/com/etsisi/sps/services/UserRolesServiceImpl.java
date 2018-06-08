package com.etsisi.sps.services;

import com.etsisi.sps.daos.UserRoleDao;
import com.etsisi.sps.entities.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("userRolesService")
@Transactional
public class UserRolesServiceImpl implements UserRolesService {
	
	@Autowired
	UserRoleDao dao;
	
	public UserRoles findById(int id) {
		return dao.findById(id);
	}

	public UserRoles findByRole(String role){
		return dao.findByRole(role);
	}

	public List<UserRoles> findAll() {
		return dao.findAll();
	}
}
