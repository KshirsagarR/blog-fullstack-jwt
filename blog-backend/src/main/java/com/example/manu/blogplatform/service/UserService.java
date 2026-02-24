package com.example.manu.blogplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.manu.blogplatform.entity.Role;
import com.example.manu.blogplatform.entity.User;
import com.example.manu.blogplatform.repository.RoleRepo;
import com.example.manu.blogplatform.repository.UserRepo;

@Service
public class UserService implements Userservice_interface {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

  
    

//    @Override
//    public User register(User user) {
//
//        Role role = roleRepository.findByName("USER");
//
//        if (role == null) {
//            throw new RuntimeException("USER role not found");
//        }
//
//        user.setRole(role);
//
//        return userRepository.save(user);
//    }
  
  public User register(User user) {

	    // encode password
	    user.setPassword(
	        passwordEncoder.encode(user.getPassword())
	    );

	    // fetch role safely
	    Role userRole = roleRepo.findByName("USER");

	    if (userRole == null) {
	        throw new RuntimeException("USER role not found in DB");
	    }

	    user.setRole(userRole);

	    return userRepo.save(user);
	}

    @Override
    public User getById(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User assignRole(Long userId, Role role) {
        User user = getById(userId);
        user.setRole(role);
        return userRepo.save(user);
    }
}
