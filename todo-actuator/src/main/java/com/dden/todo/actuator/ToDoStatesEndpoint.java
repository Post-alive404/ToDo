package com.dden.todo.actuator;

import com.dden.todo.domain.ToDo;
import com.dden.todo.repository.ToDoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

/**
 * ToDoStatesEndpoint
 *
 * @author Denys Parshutkin
 * @version 1.0.0
 */
@Component
@Endpoint(id = "todo-stats")
public class ToDoStatesEndpoint {
    public ToDoRepository toDoRepository;

    public ToDoStatesEndpoint(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @ReadOperation
    public Stats stats(){
        return new Stats(this.toDoRepository.count(),
                this.toDoRepository.countByCompleted(true));
    }

    @ReadOperation
    public ToDo getToDo(@Selector String id){
        return this.toDoRepository.findById(id).orElse(null);
    }

    @WriteOperation
    public Operation completeToDo(@Selector String id){
        ToDo toDo = this.toDoRepository.findById(id).orElse(null);
        if (null != toDo){
            toDo.setCompleted(true);
            this.toDoRepository.save(toDo);
            return new Operation("COMPLETED", true);
        }

        return new Operation("COMPLETED", false);
    }

    @DeleteOperation
    public Operation removeToDo(@Selector String id){
        try{
            this.toDoRepository.deleteById(id);
            return new Operation("DELETED", true);
        } catch (Exception ex){
            return new Operation("DELETED", false);
        }
    }


}
