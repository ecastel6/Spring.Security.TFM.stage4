package com.etsisi.sps.dao;

import com.etsisi.sps.model.UserRoles;

import java.util.List;


public interface UserRoleDao {

	List<UserRoles> findAll();
	
	UserRoles findByRole(String role);
	
	UserRoles findById(int id);
}
