package com.dden.todo;

import com.dden.todo.domain.ToDo;
import com.dden.todo.repository.ToDoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToDoSimpleMockBeanTests {
    @MockBean
    private ToDoRepository repository;

    @Test
    public void toDoTest(){
        given(this.repository.findById("my-id"))
                .willReturn(Optional.of(new ToDo("Read a Book")));
        assertThat(this.repository.findById("my-id").get().getDescription()).isEqualTo("Read a Book");

    }
}
