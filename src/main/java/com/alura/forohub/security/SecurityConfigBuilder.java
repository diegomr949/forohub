package com.alura.forohub.security;

import com.alura.forohub.service.UserDetailsServiceImpl;

public class SecurityConfigBuilder {
    private UserDetailsServiceImpl userDetailsService;
    private TokenService tokenService;

    public SecurityConfigBuilder setUserDetailsService(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
        return this;
    }

    public SecurityConfigBuilder setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
        return this;
    }

    public SecurityConfig createSecurityConfig() {
        return new SecurityConfig(userDetailsService, tokenService);
    }
}