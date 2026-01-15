package com.lib.spring.auth;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;

	public JwtAuthenticationFilter(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = header.substring(7);

		try {
			Claims claims = jwtService.parseToken(token);

			String username = claims.getSubject();

			List<String> roles = claims.get("roles", List.class);

			List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).toList();

			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
					authorities);

			if (SecurityContextHolder.getContext().getAuthentication() == null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}

		} catch (ExpiredJwtException e) {
			throw new MyCustomJwtExpiredException("Token expired at: " + e.getClaims().getExpiration(), e);
		} catch (MalformedJwtException e) {
			throw new MyCustomJwtMalformedException("Token is malformed", e);
		}

		filterChain.doFilter(request, response);
	}

	// Custom exception example (unchecked exception, inherits from RuntimeException
	// for simplicity)
	class MyCustomJwtExpiredException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public MyCustomJwtExpiredException(String message, Throwable cause) {
			super(message, cause);
		}
	}

	// Custom exception example (checked exception, inherits from Exception)
	class MyCustomJwtMalformedException extends Exception {
		private static final long serialVersionUID = 7226007910634769229L;

		public MyCustomJwtMalformedException(String message, Throwable cause) {
			super(message, cause);
		}
	}
}