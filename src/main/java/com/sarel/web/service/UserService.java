package com.sarel.web.service;

import com.sarel.web.model.User;

public interface UserService {
	
	void save(User user);

	User findById(int id);
	
	User findBySso(String sso);
	
}
