package com.dden.todo.controller;

import com.dden.todo.domain.ToDo;

import com.dden.todo.repository.ToDoRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RestController
public class ToDoController {
    private ToDoRepository toDoRepository;

    public ToDoController(ToDoRepository toDoRepository){
        this.toDoRepository = toDoRepository;
    }

    @GetMapping("/todo")
    public Flux<ToDo> getToDos(){

        return this.toDoRepository.findAll();
    }

    @GetMapping("/todo/{id}")
    public Mono<ToDo> getToDos(@PathVariable String id){
        return this.toDoRepository.findById(id);
    }


}
