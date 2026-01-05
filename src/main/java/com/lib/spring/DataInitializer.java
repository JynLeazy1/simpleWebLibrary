package com.lib.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lib.spring.users.AppUserService;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(AppUserService userService) {
        return args -> {

            if (!userService.existsByUsername("admin")) {
                userService.createUser(
                        "admin",
                        "admin123",
                        "ROLE_ADMIN",
                        "admin@admin.com"
                );
            }
        };
    }
}
