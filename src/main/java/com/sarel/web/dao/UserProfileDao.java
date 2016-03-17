package com.sarel.web.dao;

import java.util.List;

import com.sarel.web.model.UserProfile;
 
public interface UserProfileDao {
 
    List<UserProfile> findAll();
     
    UserProfile findByType(String type);
     
    UserProfile findById(int id);
}
