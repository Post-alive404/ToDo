package com.dden.todo;

import com.dden.todo.domain.ToDo;
import com.dden.todo.repository.ToDoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ToDoDataJpaTests {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ToDoRepository toDoRepository;

    @Test
    public void toDoDataTest() throws Exception{
        this.entityManager.persist(new ToDo("Read a Book"));
        Iterable<ToDo> toDos = this.toDoRepository.findAll();
        assertThat(toDos.iterator().next()).toString().contains("Read a Book");
    }
}
