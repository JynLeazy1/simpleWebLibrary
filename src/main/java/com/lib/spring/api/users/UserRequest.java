package com.lib.spring.api.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequest {

	@NotBlank
	private String username;
	@NotBlank
	private String password;
	private Role role;
	@Email
	private String email;

	public UserRequest() {
	}

	public UserRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public UserRequest(String username, String password, Role role, String email) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserRequest [username=" + username + ", role=" + role + ", email=" + email + "]";
	}
}
