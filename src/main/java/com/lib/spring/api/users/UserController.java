package com.lib.spring.api.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	private final AppUserService userService;
	
	public UserController(AppUserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	AppUserRepository userRepository;
	
	@PostMapping("/api/user")
	public ResponseEntity<?> createUser(@RequestBody UserRequest request) {
		userService.createUser(
				request.getUsername(),
				request.getPassword(),
				request.getRole(),
				request.getEmail()
				);
		return ResponseEntity.ok().build();
	}
	

	@GetMapping("/api/users")
	public List<AppUser> retreiveAllUsers(){
		return userRepository.findAll();
	}

}
