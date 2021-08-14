package com.barcodegenerator.barcodegenerator.api.config;

import com.barcodegenerator.barcodegenerator.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final UserService userService;
    private final PasswordEncoder encoder;

    public SecurityConfig(UserService userService ,PasswordEncoder encoder){
        this.userService = userService;
        this.encoder = encoder;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(encoder);
    }
}
