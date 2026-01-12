package com.lib.spring;

import com.lib.spring.api.users.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


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
