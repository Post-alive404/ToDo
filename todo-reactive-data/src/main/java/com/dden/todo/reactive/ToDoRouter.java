package com.dden.todo.reactive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ToDoRouter {
    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(ToDoHandler toDoHandler) {
        return route(GET("/todo/{id}").and(accept(MediaType.APPLICATION_JSON)),
                        toDoHandler::getToDo)
                .andRoute(GET("/todo").and(accept(MediaType.APPLICATION_JSON)),
                        toDoHandler::getToDos)
                .andRoute(POST("/todo").and(accept(MediaType.APPLICATION_JSON)),
                        toDoHandler::newToDo);
    }
}
