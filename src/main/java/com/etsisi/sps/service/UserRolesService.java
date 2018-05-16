package com.etsisi.sps.service;

import com.etsisi.sps.model.UserRoles;

import java.util.List;


public interface UserRolesService {

	UserRoles findById(int id);

	UserRoles findByRole(String role);
	
	List<UserRoles> findAll();
	
}
