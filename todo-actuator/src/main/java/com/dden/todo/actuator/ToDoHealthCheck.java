package com.dden.todo.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * ToDoHealthIndicator
 *
 * @author Denys Parshutkin
 * @version 1.0.0
 */

@Component
public class ToDoHealthCheck implements HealthIndicator {
    private String path;

    public ToDoHealthCheck(@Value("${todo.path:/tmp}") String path){
        this.path = path;
    }
    @Override
    public Health health() {
        try {
            File file = new File(path);
            if (file.exists()){
                if (file.canWrite())
                    return Health.up().build();
                return Health.down().build();
            } else {
                return Health.outOfService().build();
            }

        } catch (Exception e){
            return Health.down(e).build();
        }
    }
}
