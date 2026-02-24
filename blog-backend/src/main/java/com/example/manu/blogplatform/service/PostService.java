package com.example.manu.blogplatform.service;

	import java.time.LocalDateTime;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.data.domain.Page;
	import org.springframework.data.domain.PageRequest;
	import org.springframework.stereotype.Service;

import com.example.manu.blogplatform.enm.Poststatus;
import com.example.manu.blogplatform.entity.Post;
import com.example.manu.blogplatform.entity.User;
import com.example.manu.blogplatform.repository.PostRepo;

	@Service
	public class PostService implements Postservice_interface {

	    @Autowired
	    private PostRepo postRepo;

	    // ✅ Create Post
	    @Override
	    public Post createPost(Post post, User user) {
	        post.setCreatedBy(user);
	        post.setCreatedAt(LocalDateTime.now());
	        post.setStatus(Poststatus.PENDING);
	        return postRepo.save(post);
	    }

	    // ✅ Approved feed posts
	    @Override
	    public Page<Post> getApprovedPosts(int page, int size) {
	        return postRepo.findByStatus(
	                Poststatus.APPROVED,
	                PageRequest.of(page, size)
	        );
	    }

	    // ✅ My posts
	    @Override
	    public Page<Post> getMyPosts(User user, int page, int size) {
	        return postRepo.findByCreatedBy(
	                user,
	                PageRequest.of(page, size)
	        );
	    }

	    // ✅ Admin — pending posts
	    @Override
	    public Page<Post> getPendingPosts(int page, int size) {
	        return postRepo.findByStatusOrderByCreatedAtDesc(
	                Poststatus.PENDING,
	                PageRequest.of(page, size)
	        );
	    }

	    // ✅ Approve
	    @Override
	    public Post approvePost(Long postId) {
	        Post post = postRepo.findById(postId)
	                .orElseThrow(() -> new RuntimeException("Post not found"));

	        post.setStatus(Poststatus.APPROVED);
	        return postRepo.save(post);
	    }

	    // ✅ Reject
	    @Override
	    public Post rejectPost(Long postId) {
	        Post post = postRepo.findById(postId)
	                .orElseThrow(() -> new RuntimeException("Post not found"));

	        post.setStatus(Poststatus.PENDING); // or create REJECTED enum later
	        return postRepo.save(post);
	    }
	    
	    @Override
	    public Post updatePost(Long postId, Post updatedPost, User user) {
	        Post post = postRepo.findById(postId)
	                .orElseThrow(() -> new RuntimeException("Post not found"));

	        // only owner can update
	        if (!post.getCreatedBy().getId().equals(user.getId())) {
	            throw new RuntimeException("Not allowed");
	        }

	        post.setTitle(updatedPost.getTitle());
	        post.setContent(updatedPost.getContent());

	        return postRepo.save(post);
	    }
	    
	    @Override
	    public void deletePost(Long postId, User user) {
	        Post post = postRepo.findById(postId)
	                .orElseThrow(() -> new RuntimeException("Post not found"));

	        if (!post.getCreatedBy().getId().equals(user.getId())) {
	            throw new RuntimeException("Not allowed");
	        }

	        postRepo.delete(post);
	    }
	    
	    @Override
	    public void adminDeletePost(Long postId) {
	        postRepo.deleteById(postId);
	    }
	}


