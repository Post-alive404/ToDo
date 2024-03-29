package com.dden.todo.client.service;

import com.dden.todo.client.domain.ToDo;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ToDoService {

    private RestTemplate restTemplate;
    public ToDoService(RestTemplateBuilder builder){
        this.restTemplate = builder.build();
    }

    public ToDo findById(String id){
        return this.restTemplate.getForObject("/todo/{id}",ToDo.class,id);
    }
}
