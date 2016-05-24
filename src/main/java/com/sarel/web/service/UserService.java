package com.sarel.web.service;

import java.util.List;

import com.sarel.web.model.User;

public interface UserService {
	
	void save(User user);

	User findById(int id);
	
	User findBySso(String sso);
	
	List<User> findAllUsersByRol(String rol);
	
}
