package com.dden.todo.config;

import com.dden.todo.domain.ToDo;
import com.dden.todo.redis.ToDoProducer;
import org.springframework.beans.factory.annotation.Value;
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

    @Bean
    CommandLineRunner sendMessage(ToDoProducer producer, @Value("${todo.redis.topic}") String topic){
        return args -> producer.sendTo(topic, new ToDo("Read a new Spring Book!"));
    }
}
