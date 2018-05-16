package com.etsisi.sps.service;

import com.etsisi.sps.model.User;

import java.util.List;


public interface UserService {
	
	User findById(int id);
	
	User findByUsername(
			String username);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserByUsername(String username);

	List<User> findAllUsers(); 
	
	boolean isUserUsernameUnique(Integer id, String username);

	boolean isUserEmailValid(String email);

}