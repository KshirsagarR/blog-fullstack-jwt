package com.example.manu.blogplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.manu.blogplatform.entity.Post;
import com.example.manu.blogplatform.entity.User;
import com.example.manu.blogplatform.security.CustomUserDetails;
import com.example.manu.blogplatform.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	  @Autowired
	    private PostService postService;

	    // ✅ create post (USER)
	    @PostMapping()
	    public Post createPost(@RequestBody Post post,
	    		@AuthenticationPrincipal CustomUserDetails cud ) {
	        return postService.createPost(post, cud.getUser());
	    }

	    // ✅ my posts (USER + pagination)
	    @GetMapping("/my")
	    public Page<Post> myPosts(
	            @AuthenticationPrincipal CustomUserDetails cud,
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "5") int size) {

	        return postService.getMyPosts(cud.getUser(), page, size);
	    }

	    // ✅ approved feed (all users)
	    @GetMapping("/feed")
	    public Page<Post> feed(@RequestParam int page,
	                           @RequestParam int size,
	           	            @AuthenticationPrincipal CustomUserDetails cud)
	    {
	        return postService.getApprovedPosts(page, size);
	    }

	    // ✅ update my post
	    @PutMapping("/{id}")
	    public Post update(@PathVariable Long id,
	                       @RequestBody Post post,
	                       @AuthenticationPrincipal CustomUserDetails cud) {
	        return postService.updatePost(id,post, cud.getUser());
	    }

	    // ✅ delete my post
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id,
                @AuthenticationPrincipal CustomUserDetails cud) {
	        postService.deletePost(id, cud.getUser());
	    }

	    // ================= ADMIN =================

	    // ✅ pending posts
	    @PreAuthorize("hasRole('ADMIN')")
	    @GetMapping("/admin/pending")
	    public Page<Post> pending(@RequestParam int page,
	                              @RequestParam int size) {
	        return postService.getPendingPosts(page, size);
	    }

	    // ✅ approve post
	   @PreAuthorize("hasRole('ADMIN')")
	    @PutMapping("/admin/{id}/approve")
	    public Post approve(@PathVariable Long id) {
	        return postService.approvePost(id);
	    }

	    // ✅ admin delete any post
	    @PreAuthorize("hasRole('ADMIN')")
	    @DeleteMapping("/admin/{id}")
	    public void adminDelete(@PathVariable Long id) {
	        postService.adminDeletePost(id);
	    }
}



