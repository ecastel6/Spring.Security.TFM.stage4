package com.etsisi.sps.daos;

import com.etsisi.sps.entities.User;

import java.util.List;


public interface UserDao {

	User findById(int id);
	
	User findByUsername(String username);
	
	void save(User user);
	
	void deleteByUsername(String username);
	
	List<User> findAllUsers();

}

