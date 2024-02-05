package com.dden.todo.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * ToDoActuatorSecurity
 *
 * @author Denys Parshutkin
 * @version 1.0.0
 */
public class ToDoActuatorSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(r -> r
                        .requestMatchers(EndpointRequest.toAnyEndpoint())
                        )
                .authorizeHttpRequests(r -> r
                        .anyRequest()
                        .hasRole("ENDPOINT_ADMIN"))
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
