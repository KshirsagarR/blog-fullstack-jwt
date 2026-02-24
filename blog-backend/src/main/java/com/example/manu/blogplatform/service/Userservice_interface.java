package com.example.manu.blogplatform.service;

import com.example.manu.blogplatform.entity.Role;
import com.example.manu.blogplatform.entity.User;

public interface Userservice_interface {


	    User register(User user);


	    User getById(Long userId);

	    User assignRole(Long userId, Role role);
	}


