package com.dden.todo.repository;

import com.dden.todo.domain.ToDo;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<ToDo, String> {
    public long countByCompleted(boolean completed);
}
