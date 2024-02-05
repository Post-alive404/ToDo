package com.dden.todo.redis;

import com.dden.todo.domain.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
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

    private RedisTemplate<String, ToDo> template;


    public ToDoProducer(RedisTemplate<String, ToDo> template) {
        this.template = template;
    }

    public void sendTo(String topic, ToDo toDo){
        log.info("Producer> ToDo sent");
        this.template.convertAndSend(topic, toDo);
    }
}
