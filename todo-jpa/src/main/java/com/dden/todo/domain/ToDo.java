package com.dden.todo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
public class ToDo {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid" )
    private String id;
    @NotNull
    @NotBlank
    private String description;
//    @JsonFormat(pattern="dd.MM.yyyy HH:mm:ss")
    @Column(insertable = true, updatable = false)
    private LocalDateTime created;
//    @JsonFormat(pattern="dd.MM.yyyy HH:mm:ss")
    private LocalDateTime modified;
    private boolean completed;
    public ToDo(String description){
        this.description = description;
    }
    @PrePersist
    void onCreate(){
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
    }

    @PreUpdate
    void onUpdate(){
        this.setModified(LocalDateTime.now());
    }

}
