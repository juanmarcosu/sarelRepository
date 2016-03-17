package com.sarel.web.dao;

import com.sarel.web.model.User;

public interface UserDao {
	
	void save(User user);

	User findById(int id);
	
	User findBySSO(String sso);
	
}

