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
@ConfigurationProperties(prefix = "todo")
public class ToDoProperties {
    private String path;
}
