package com.etsisi.sps.service;

import com.etsisi.sps.dao.UserRoleDao;
import com.etsisi.sps.model.UserRoles;
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
