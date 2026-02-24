package com.example.manu.blogplatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
	@Table(name="users_info")
	public class User {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
@Column(name="Username")
	    private String username;
@Column(name="Password")
	    private String password;

        @ManyToOne(fetch = FetchType.EAGER)
        
        @JoinColumn(name="role_id",nullable = false)
	    private Role role; // ADMIN / USER

	    public User() {}

	    public User(String username, String password, Role role) {
	        this.username = username;
	        this.password = password;
	        
	        
	        this.role = role;      
	        
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}
	    
	    

}
