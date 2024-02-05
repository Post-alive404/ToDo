package com.dden.todo.config;

import com.dden.todo.interceptor.ToDoMetricInterceptor;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.MappedInterceptor;

/**
 * ToDoConfig
 *
 * @author Denys Parshutkin
 * @version 1.0.0
 */

@EnableConfigurationProperties(ToDoProperties.class)
@Configuration
public class ToDoConfig {
    @Bean
    public MappedInterceptor metricInterceptor(MeterRegistry registry){
        return new MappedInterceptor(new String[]{"/**"}, new ToDoMetricInterceptor(registry));
    }
}
