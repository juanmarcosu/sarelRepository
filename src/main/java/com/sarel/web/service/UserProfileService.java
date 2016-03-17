package com.sarel.web.service;

import java.util.List;

import com.sarel.web.model.UserProfile;
 
public interface UserProfileService {
 
    List<UserProfile> findAll();
     
    UserProfile findByType(String type);
     
    UserProfile findById(int id);
}
