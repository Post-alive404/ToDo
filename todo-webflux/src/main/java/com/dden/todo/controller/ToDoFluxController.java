package com.dden.todo.controller;

import com.dden.todo.domain.ToDo;
import com.dden.todo.repository.ToDoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ToDoFluxController {

    private ToDoRepository repository;

    public ToDoFluxController(ToDoRepository repository){
        this.repository = repository;
    }

    @GetMapping("/todo-flux")
    public Flux<ToDo> getToDos() {
        return  Flux.fromIterable(this.repository.findAll().toIterable());
    }
}
