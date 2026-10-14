package com.example.base.core.config;

import org.springframework.security.core.Authentication;

public interface JwtService {

    String generateToken(Authentication authentication);
    Authentication getAuthentication(String token);
}
