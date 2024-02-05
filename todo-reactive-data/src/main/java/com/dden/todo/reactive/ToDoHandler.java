package com.dden.todo.reactive;

import com.dden.todo.domain.ToDo;
import com.dden.todo.repository.ToDoRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ToDoHandler {
    private ToDoRepository repository;

    public ToDoHandler(ToDoRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> getToDo(ServerRequest request){
        return findById(request.pathVariable("id"));
    }
    public Mono<ServerResponse> getToDos(ServerRequest request){
        Flux<ToDo> toDos = this.repository.findAll();
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(toDos, ToDo.class);
    }
    public Mono<ServerResponse> newToDo(ServerRequest request){
        Mono<ToDo> toDo = request.bodyToMono(ToDo.class);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(toDo.flatMap(this::save), ToDo.class));
    }
    private Mono<ServerResponse> findById(String id){
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<ToDo> toDo = this.repository.findById(id);
        return  toDo
                .flatMap(t -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromObject(t)))
                .switchIfEmpty(notFound);
    }

    private Mono<ToDo> save(ToDo toDo){
        return Mono.fromSupplier(
                () -> {
                 repository
                         .save(toDo)
                         .subscribe();
                 return toDo;
                });
    }


}
