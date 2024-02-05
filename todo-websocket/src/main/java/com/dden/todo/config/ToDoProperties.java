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
@ConfigurationProperties(prefix = "todo.ws")
public class ToDoProperties {

    private String app = "/todo-api-ws";
    private String broker = "/todo";
    private String endpoint = "/stomp";
}
