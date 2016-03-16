package com.sarel.web.dao;

import com.sarel.web.model.User;

public interface UserDao {

	User findById(int id);
	
	User findBySSO(String sso);
	
}

