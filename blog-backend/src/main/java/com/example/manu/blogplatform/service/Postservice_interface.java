package com.example.manu.blogplatform.service;

import org.springframework.data.domain.Page;

import com.example.manu.blogplatform.entity.Post;
import com.example.manu.blogplatform.entity.User;

public interface Postservice_interface {
	
	
	    Post createPost(Post post, User user);

	    Page<Post> getApprovedPosts(int page, int size);

	    Page<Post> getMyPosts(User user, int page, int size);

	    Page<Post> getPendingPosts(int page, int size);

	    Post approvePost(Long postId);

	    Post rejectPost(Long postId);
	    
	    Post updatePost(Long postId, Post updatedPost, User user);

	    void deletePost(Long postId, User user);

	    void adminDeletePost(Long postId);
	    
	    
	   
	}

	
	


