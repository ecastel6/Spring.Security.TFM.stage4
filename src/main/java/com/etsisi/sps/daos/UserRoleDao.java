package com.etsisi.sps.daos;

import com.etsisi.sps.entities.UserRoles;

import java.util.List;


public interface UserRoleDao {

	List<UserRoles> findAll();
	
	UserRoles findByRole(String role);
	
	UserRoles findById(int id);
}
