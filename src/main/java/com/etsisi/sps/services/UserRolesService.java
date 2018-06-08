package com.etsisi.sps.services;

import com.etsisi.sps.entities.UserRoles;

import java.util.List;


public interface UserRolesService {

	UserRoles findById(int id);

	UserRoles findByRole(String role);
	
	List<UserRoles> findAll();
	
}
