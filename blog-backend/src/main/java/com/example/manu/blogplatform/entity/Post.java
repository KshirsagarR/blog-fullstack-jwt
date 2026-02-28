package com.example.manu.blogplatform.entity;

import java.time.LocalDateTime;

import com.example.manu.blogplatform.enm.Poststatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;




@Entity
@Table(name="post_info")
public class Post {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String title;
	    private String content;

	    private LocalDateTime createdAt;

	    @Enumerated(EnumType.STRING)
	    private Poststatus status;

	    @ManyToOne
	    @JoinColumn(name = "user_id")
	   private User createdBy;

		public Post() {
			super();
			
		}

		public Post(String title, String content, LocalDateTime createdAt, Poststatus status, User createdBy) {
			super();
			this.title = title;
			this.content = content;
			this.createdAt = createdAt;
			this.status = status;
			this.createdBy = createdBy;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
		this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public Poststatus getStatus() {
			return status;
		}

		public void setStatus(Poststatus status) {
			this.status = status;
		}

		public User getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(User createdBy) {
			this.createdBy = createdBy;
		}
	    
	    
		
}



