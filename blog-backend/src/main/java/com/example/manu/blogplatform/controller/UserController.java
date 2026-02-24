package com.example.manu.blogplatform.controller;



	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.manu.blogplatform.entity.Role;
import com.example.manu.blogplatform.entity.User;
import com.example.manu.blogplatform.repository.RoleRepo;
import com.example.manu.blogplatform.service.Userservice_interface;

	@RestController
	@RequestMapping("api/users")
	public class UserController {

	    @Autowired
	    private Userservice_interface userService;
	    
	    @Autowired
	    private RoleRepo roleRepo;


	    @GetMapping("/{id}")
	    public User getUser(@PathVariable Long id) {
	        return userService.getById(id);
	    }
	    
	    @PreAuthorize("hasRole('ADMIN')")
	    @PostMapping("/assign-role/{userId}")
	    public User assignRole(
	            @PathVariable Long userId,
	            @RequestParam String roleName) {

	        Role role = roleRepo.findByName(roleName);
	        return userService.assignRole(userId, role);
	    }


	}



