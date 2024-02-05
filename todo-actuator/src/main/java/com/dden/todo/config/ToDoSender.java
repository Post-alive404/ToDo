package com.dden.todo.config;

import com.dden.todo.domain.ToDo;
import com.dden.todo.repository.ToDoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ToDoSender
 *
 * @author Denys Parshutkin
 * @version 1.0.0
 */

@Configuration
public class ToDoSender {
    private Logger log = LoggerFactory.getLogger(ToDoSender.class);

    private ToDoRepository repository;

    public ToDoSender(ToDoRepository repository) {
        this.repository = repository;
    }


    @Bean
    CommandLineRunner senMessage(ToDoRepository repository){
        return args -> {
            ToDo toDo1 = new ToDo("workout tomorrow morning!", false);
            this.repository.save(toDo1);
            log.info("new " + toDo1);
            this.repository.save(new ToDo("read book", false));
            this.repository.save(new ToDo("do English", true));
        };
    }
}
