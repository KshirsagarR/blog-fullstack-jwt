package com.example.manu.blogplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.manu.blogplatform.enm.Poststatus;
import com.example.manu.blogplatform.entity.Post;
import com.example.manu.blogplatform.entity.User;

	public interface PostRepo extends JpaRepository<Post, Long> {

	    // ✅ Approved posts → feeds page
	    
		Page<Post> findByStatus(Poststatus status, Pageable pageable);

	    // ✅ My posts → user dashboard
	    
		Page<Post> findByCreatedBy(User user, Pageable pageable);

	    // ✅ Admin → pending posts
	    
		Page<Post> findByStatusOrderByCreatedAtDesc(Poststatus status, Pageable pageable);

	    // ✅ Optional — approved posts except my own
	    
		Page<Post> findByStatusAndCreatedByNot(Poststatus status, User user, Pageable pageable);
	}

