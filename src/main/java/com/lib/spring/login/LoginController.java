package com.lib.spring.login;


import com.lib.spring.*;
import com.lib.spring.users.UserRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
	
	@PostMapping("/api/login")
	public ResponseEntity<?> login(@RequestBody UserRequest request) {

	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(
	            request.getUsername(),
	            request.getPassword()
	        )
	    );

	    SecurityContextHolder.getContext().setAuthentication(authentication);

	    return ResponseEntity.ok().build();
	}

}
