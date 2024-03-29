package com.dden.todo;

import com.dden.todo.controller.ToDoController;
import com.dden.todo.domain.ToDo;
import com.dden.todo.repository.ToDoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ToDoController.class)
public class ToDoWebMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ToDoRepository toDoRepository;

    @Test
    public void toDoControllerTest() throws Exception{
        given(this.toDoRepository.findById("my-id"))
                .willReturn(Optional.of(new ToDo("Do Homework", true)));

        this.mvc.perform(get("/api/todo/my-id").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string(
                        "{\"id\":\"my-id\",\"description\":\"Do Homework\",\"completed\":true}"));

    }
}
