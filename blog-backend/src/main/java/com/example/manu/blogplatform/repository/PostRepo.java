package com.example.manu.blogplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.manu.blogplatform.enm.Poststatus;
import com.example.manu.blogplatform.entity.Post;
import com.example.manu.blogplatform.entity.User;

	public interface PostRepo extends JpaRepository<Post, Long> {

	    // Approved posts feed page
	    
		Page<Post> findByStatus(Poststatus status, Pageable pageable);

	    // My posts
	    
		Page<Post> findByCreatedBy(User user, Pageable pageable);

	    // Admin pending posts status 
	    
		Page<Post> findByStatusOrderByCreatedAtDesc(Poststatus status, Pageable pageable);

	    
	    
		Page<Post> findByStatusAndCreatedByNot(Poststatus status, User user, Pageable pageable);
	}

