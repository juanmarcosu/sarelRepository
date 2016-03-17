package com.sarel.web.model;

public enum UserProfileType {
	CONSULTOR("CONSULTOR"),
	LABORATORISTA("LABORATORISTA"),
	ADMINISTRADOR("ADMINISTRADOR");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
