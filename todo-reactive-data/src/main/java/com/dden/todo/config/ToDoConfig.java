package com.dden.todo.config;

import com.dden.todo.domain.ToDo;
import com.dden.todo.repository.ToDoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.dden.todo.repository")
public class ToDoConfig extends AbstractReactiveMongoConfiguration {


    @Bean
    public CommandLineRunner insertAndView(ToDoRepository repository,
                                           ApplicationContext context){
        return args -> {
            repository.save(new ToDo("Do homework")).subscribe();
            repository.save(new ToDo("Workout in the mornings", true)).
                    subscribe();
            repository.save(new ToDo("Make dinner tonight")).subscribe();
            repository.save(new ToDo("Clean the studio", true)).subscribe();
            repository.findAll().subscribe(System.out::println);
        };
    }

    @Override
    protected String getDatabaseName() {
        return "todos";
    }
}
