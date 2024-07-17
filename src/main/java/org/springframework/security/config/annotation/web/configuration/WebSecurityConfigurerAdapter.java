package org.springframework.security.config.annotation.web.configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public abstract class WebSecurityConfigurerAdapter {

    protected String authenticationManagerBean() throws Exception {
        return super.toString();
    }

    protected abstract void configure(HttpSecurity http) throws Exception;

    protected abstract void configure(AuthenticationManagerBuilder auth) throws Exception;
}
