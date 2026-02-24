package com.example.manu.blogplatform.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.manu.blogplatform.entity.User;
import com.example.manu.blogplatform.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	

	    @Autowired
	    private UserRepo userRepo;

	    @Override
	    public UserDetails loadUserByUsername(String username)
	            throws UsernameNotFoundException {
	        System.out.println("LOGIN TRY → " + username);


	        User user = userRepo.findByUsername(username)
	                .orElseThrow(() ->
	                        new UsernameNotFoundException("User not found"));

	        return new CustomUserDetails(user);
	    }

}
