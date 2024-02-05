package com.dden.todo.jms;

import com.dden.todo.domain.ToDo;
import com.dden.todo.repository.ToDoRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * ToDoConsumer
 *
 * @author Denys Parshutkin
 * @version 1.0.0
 */
@Component
public class ToDoConsumer {
    private Logger log = LoggerFactory.getLogger(ToDoConsumer.class);

    private ToDoRepository repository;

    public ToDoConsumer(ToDoRepository repository) {
        this.repository = repository;
    }

    @JmsListener(destination = "${todo.jms.destination}", containerFactory = "jmsFactory")
    public void processToDo(@Valid ToDo toDo){
        log.info("Consumer> " + toDo);
        log.info("ToDo created> " + this.repository.save(toDo));
    }
}
