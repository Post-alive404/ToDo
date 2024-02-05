package com.dden.todo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ToDoProperties
 *
 * @author Denys Parshutkin
 * @version 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "todo.authentication")
public class ToDoProperties {

    private String findByEmailUri;
    private String username;
    private String password;
}
