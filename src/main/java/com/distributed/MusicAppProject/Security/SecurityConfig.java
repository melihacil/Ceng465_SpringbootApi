package com.distributed.MusicAppProject.Security;

import com.distributed.MusicAppProject.config.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {//extends WebSecurityConfigurerAdapter{


    private JwtAuthFilter jwtAuthFilter;
    //private final UserService 33:31

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user1").password("password").roles("USER");
    }
}