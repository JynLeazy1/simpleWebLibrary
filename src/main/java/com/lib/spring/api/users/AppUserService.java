package com.lib.spring.api.users;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository repository,
                          PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(String username, String rawPassword, String role, String email) {
    	
    	if (repository.existsByUsername(username)) {
            throw new IllegalStateException("User already exists");
        }
    	
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(role);
        user.setEmail(email);
        repository.save(user);
    }
    
    public boolean existsByUsername(String username) {
        return repository.findByUsername(username).isPresent();
    }
}
