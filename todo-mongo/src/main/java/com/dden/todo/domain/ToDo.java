package com.dden.todo.domain;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Document
public class ToDo {

    @NotNull
    @Id
    private String id;
    @NotNull
    @NotBlank
    private String description;
//    @JsonFormat(pattern="dd.MM.yyyy HH:mm:ss")

    private LocalDateTime created;
//    @JsonFormat(pattern="dd.MM.yyyy HH:mm:ss")
    private LocalDateTime modified;
    private boolean completed;
    public ToDo(){
        LocalDateTime date = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
        this.created = date;
        this.modified = date;
    }
    public ToDo(String description){
        this.description = description;
    }

    public ToDo(String description, boolean completed){
        this();
        this.description = description;
        this.completed = completed;
    }

}
