package com.dden.todo.interceptor;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * ToDoMetricInterceptor
 *
 * @author Denys Parshutkin
 * @version 1.0.0
 */
public class ToDoMetricInterceptor implements HandlerInterceptor {
    private static Logger log = LoggerFactory.getLogger(ToDoMetricInterceptor.class);

    private MeterRegistry registry;
    private String URI, pathKey, METHOD;

    public ToDoMetricInterceptor(MeterRegistry registry){
        this.registry = registry;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
        URI = request.getRequestURI();
        METHOD = request.getMethod();
        if (!URI.contains("prometheus")){
            log.info(" >> PATH: {}", URI);
            log.info(" >> METHOD: {}", METHOD);
            pathKey = "api_".concat(METHOD.toLowerCase()).
                    concat(URI.replaceAll("/", "_").toLowerCase());
            this.registry.counter(pathKey).increment();
        }
    }
}
