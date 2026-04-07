package com.lib.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
<<<<<<< frontendDisplayBook
import java.util.List;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

=======
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import com.lib.spring.auth.AuthEntryPointJwt;
<<<<<<< HEAD:src/main/java/com/lib/spring/SecurityConfig.java
import com.lib.spring.auth.JwtAuthenticationFilter;
import com.lib.spring.auth.JwtService;
>>>>>>> main
=======
import com.lib.spring.auth.AccessTokenAuthenticationFilter;
import com.lib.spring.auth.AccessTokenService;
>>>>>>> backend:src/main/java/com/lib/spring/security/SecurityConfig.java

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final AccessTokenService accessTokenService;
	private final AuthEntryPointJwt authEntryPointJwt;

	public SecurityConfig(AccessTokenService accessTokenService, AuthEntryPointJwt authEntryPointJwt) {
		this.accessTokenService = accessTokenService;
		this.authEntryPointJwt = authEntryPointJwt;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
<<<<<<< HEAD:src/main/java/com/lib/spring/SecurityConfig.java
						.cors(Customizer.withDefaults())
=======
        		.cors(cors -> cors.configurationSource(corsConfigurationSource()))
>>>>>>> backend:src/main/java/com/lib/spring/security/SecurityConfig.java
        		.csrf(csrf -> csrf.disable())
        		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        		.exceptionHandling(ex -> ex.authenticationEntryPoint(authEntryPointJwt))
                .authorizeHttpRequests(auth -> auth
<<<<<<< HEAD:src/main/java/com/lib/spring/SecurityConfig.java
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                		.requestMatchers(HttpMethod.POST, "/api/user").permitAll()
=======
                		.requestMatchers(HttpMethod.POST, "/api/users").permitAll()
>>>>>>> backend:src/main/java/com/lib/spring/security/SecurityConfig.java
                		.requestMatchers(HttpMethod.GET, "/api/users").permitAll()
                		.requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                		.requestMatchers(HttpMethod.POST, "/api/auth/refresh").permitAll()
                		.requestMatchers(HttpMethod.POST, "/api/auth/logout").authenticated()
                		.requestMatchers(HttpMethod.GET, "/api/uploads/image/**").permitAll()
                		.requestMatchers(HttpMethod.POST, "/api/uploads/image").authenticated()
                		.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                		.requestMatchers("/error").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new AccessTokenAuthenticationFilter(accessTokenService), UsernamePasswordAuthenticationFilter.class)
                .formLogin(form -> form.disable())
                .logout(logout -> logout.disable())
                .build();
    }

<<<<<<< frontendDisplayBook
		@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("http://localhost:5174"));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}


=======
	@Bean
	public AuthenticationManager authenticationManager(
	        AuthenticationConfiguration config) throws Exception {
	    return config.getAuthenticationManager();
	}
>>>>>>> main

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(List.of("http://localhost:5173", "http://192.168.1.13:5173"));
		config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowedHeaders(List.of("*"));
		config.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
}
