package com.distributed.MusicAppProject.config.Security;

import com.distributed.MusicAppProject.config.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
@RequiredArgsConstructor
@Configuration
//@EnableWebSecurity
public class SecurityConfig {


    private JwtAuthFilter jwtAuthFilter;
    //private final UserService 33:31
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // disable CSRF protection
//                .authorizeHttpRequests(req ->
////                req.requestMatchers("app/v1/auth/**")
////                        .permitAll()
////                        .anyRequest()
////                        .authenticated()
////                )
                .authorizeHttpRequests().anyRequest().permitAll();
//                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .httpBasic(Customizer.withDefaults());
        return http.build();
    }



}