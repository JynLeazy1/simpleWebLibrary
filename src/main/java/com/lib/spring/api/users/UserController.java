package com.lib.spring.api.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private final AppUserService userService;
	//private final AppUserRepository userRepository;

	public UserController(AppUserService userService/*, AppUserRepository userRepository*/) {
		this.userService = userService;
		//this.userRepository = userRepository;
	}
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserRequest request) {
		userService.createUser(
				request.getUsername(),
				request.getPassword(),
				request.getRole(),
				request.getEmail()
				);
		return ResponseEntity.ok().build();
	}
	

	//@GetMapping
	//public List<AppUser> retreiveAllUsers() {
	//	return userRepository.findAll();
	//}

}
