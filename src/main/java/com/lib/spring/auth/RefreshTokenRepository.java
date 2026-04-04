package com.lib.spring.auth;

import java.util.Optional;

import com.lib.spring.api.users.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByUser(AppUser user);
}
