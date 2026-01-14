package com.lib.spring.api.users;
import com.lib.spring.api.login.LoginRequest;

public class UserRequest extends LoginRequest {
	
	
	private String role;
	private String email;
	
	
	public UserRequest() {
		super();
	}	
	
	
	public UserRequest(String username, String password) {
		super(username, password);
	}	

	public UserRequest(String username, String password, String role, String email) {
		super(username, password);
		this.role = role;
		this.email = email;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
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
		return "UserRequest [username=" + username + ", role=" + role + ", email=" + email
				+ "]";
	}
	
	
}
