package com.dden.directory.config;

import com.dden.directory.repository.PersonRepository;
import com.dden.directory.security.DirectoryUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.web.SecurityFilterChain;

/**
 * DirectorySecurityConfig
 *
 * @author Denys Parshutkin
 * @version 1.0.0
 */
@Configuration
public class DirectorySecurityConfig {

    private PersonRepository personRepository;

    public DirectorySecurityConfig(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**")
                        .hasRole("ADMIN"))
                .httpBasic(Customizer.withDefaults())
                .userDetailsService(new DirectoryUserDetailsService(this.personRepository));

        return http.build();
    }

//    @Bean
//    public AuthenticationManagerBuilder configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(new DirectoryUserDetailsService(this.personRepository));
//
//        return auth;
//    }

}
