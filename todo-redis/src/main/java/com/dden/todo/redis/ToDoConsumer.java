package com.dden.todo.redis;

import com.dden.todo.domain.ToDo;
import com.dden.todo.repository.ToDoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ToDoConsumer
 *
 * @author Denys Parshutkin
 * @version 1.0.0
 */
@Component
public class ToDoConsumer {
    private static final Logger log = LoggerFactory.getLogger(ToDoConsumer.class);

    private ToDoRepository repository;


    public ToDoConsumer(ToDoRepository repository) {
        this.repository = repository;
    }

    public void handleMessage(ToDo toDo){
        log.info("Consumer> " + toDo);
        log.info("ToDo created> " + this.repository.save(toDo));
    }
}
