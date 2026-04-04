package com.lib.spring.api.login;

import com.lib.spring.auth.AccessTokenService;
import com.lib.spring.auth.RefreshToken;
import com.lib.spring.auth.RefreshTokenService;
import com.lib.spring.api.users.SecurityUser;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AccessTokenService accessTokenService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(AuthenticationManager authenticationManager,
            AccessTokenService accessTokenService,
            RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.accessTokenService = accessTokenService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
                )
            );
            String accessToken = accessTokenService.generateToken(authentication);
            String refreshToken = refreshTokenService.createRefreshToken(authentication.getName()).getToken();
            return ResponseEntity.ok(new LoginResponse(accessToken, refreshToken));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshRequest request) {
        try {
            RefreshToken refreshToken = refreshTokenService.validateRefreshToken(request.getRefreshToken());
            UserDetails userDetails = new SecurityUser(refreshToken.getUser());
            String newAccessToken = accessTokenService.generateToken(userDetails);
            return ResponseEntity.ok(new LoginResponse(newAccessToken, refreshToken.getToken()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal UserDetails userDetails) {
        refreshTokenService.deleteByUsername(userDetails.getUsername());
        return ResponseEntity.ok().build();
    }
}
