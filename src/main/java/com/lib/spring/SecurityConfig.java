package com.lib.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
        		.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                		.requestMatchers(HttpMethod.POST, "/api/user").permitAll()
                		.requestMatchers(HttpMethod.GET, "/api/users").permitAll()
                		.requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                		.requestMatchers(HttpMethod.POST, "/api/book").permitAll()
                		.requestMatchers(HttpMethod.GET, "/api/book/*").permitAll()
                		.requestMatchers(HttpMethod.PUT, "/api/book/*").permitAll()
                		.requestMatchers(HttpMethod.DELETE, "/api/book/*").permitAll()
                		.requestMatchers(HttpMethod.GET, "/api/books").permitAll()
                		.requestMatchers(HttpMethod.GET, "/api/uploads/image/*").permitAll()
                		.requestMatchers(HttpMethod.POST, "/api/uploads/image").permitAll()
                        .requestMatchers("/login", "/error").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/books", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                )
                .build();
    }
	

		@Bean
		public AuthenticationManager authenticationManager(
		        AuthenticationConfiguration config) throws Exception {
		    return config.getAuthenticationManager();
		}


}
