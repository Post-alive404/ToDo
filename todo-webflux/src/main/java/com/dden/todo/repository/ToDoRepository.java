package com.dden.todo.repository;

import com.dden.todo.domain.ToDo;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Repository
public class ToDoRepository {
    private Flux<ToDo> toDoFlux = Flux.fromIterable(Arrays.asList(
            new ToDo("First job"),
            new ToDo("Second job"),
            new ToDo("Third job"),
            new ToDo("Fourth job")
    ));

    public Mono<ToDo> findById(String id){
        return Mono.from(toDoFlux.filter(toDo -> toDo.getId().equals(id)));
    }

    public Flux<ToDo> findAll(){
        return toDoFlux;
    }
}
