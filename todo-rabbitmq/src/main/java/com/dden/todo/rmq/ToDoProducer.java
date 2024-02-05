package com.dden.todo.rmq;

import com.dden.todo.domain.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * ToDoProducer
 *
 * @author Denys Parshutkin
 * @version 1.0.0
 */
@Component
public class ToDoProducer {
    private static final Logger log = LoggerFactory.getLogger(ToDoProducer.class);

    private RabbitTemplate template;

    public ToDoProducer(RabbitTemplate template) {
        this.template = template;
    }

    public void sendTo(String queue, ToDo toDo){
        this.template.convertAndSend(queue, toDo);
        log.info("Producer> Message Sent");
    }
}
