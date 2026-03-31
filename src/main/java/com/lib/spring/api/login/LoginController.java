package com.lib.spring.api.login;


import com.lib.spring.auth.JwtService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	public LoginController(AuthenticationManager authenticationManager, JwtService jwtService) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		try {
			Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
					request.getUsername(),
					request.getPassword()
				)
			);
			String jwt = jwtService.generateToken(authentication);
			return ResponseEntity.ok(new LoginResponse(jwt));
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}

}
