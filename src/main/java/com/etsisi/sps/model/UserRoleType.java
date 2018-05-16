package com.etsisi.sps.model;

import java.io.Serializable;

public enum UserRoleType implements Serializable{
	USER("USER"),
	DBA("DBA"),
	ADMIN("ADMIN");
	
	String UserRoleType;
	
	private UserRoleType(String userProfileType){
		this.UserRoleType = userProfileType;
	}
	
	public String getUserProfileType(){
		return UserRoleType;
	}
	
}
