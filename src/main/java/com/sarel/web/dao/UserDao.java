package com.sarel.web.dao;

import java.util.List;

import com.sarel.web.model.User;

public interface UserDao {
	
	void save(User user);

	User findById(int id);
	
	User findBySSO(String sso);
	
	List<User> findAllUsersByRol(String rol);
	
}

