package com.dden.todo.config;

import com.dden.todo.domain.ToDo;
import com.dden.todo.rmq.ToDoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ToDoSender
 *
 * @author Denys Parshutkin
 * @version 1.0.0
 */
@Configuration
@EnableScheduling
public class ToDoSender {
    @Autowired
    private ToDoProducer producer;
    @Value("${todo.amqp.queue}")
    private String destination;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedDelay = 500L)
    private void sendToDos(){
        producer.sendTo(destination, new ToDo("Thinking on Spring Boot at " + dateFormat.format(new Date())));
    }

//    @Bean
//    public CommandLineRunner sendToDos(@Value("${todo.amqp.queue}") String destination, ToDoProducer producer){
//        return args -> producer.sendTo(destination, new ToDo("workout tomorrow morning!"));
//    }
}
