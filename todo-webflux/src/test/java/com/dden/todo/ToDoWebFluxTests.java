package com.dden.todo;

import com.dden.todo.controller.ToDoController;
import com.dden.todo.controller.ToDoFluxController;
import com.dden.todo.domain.ToDo;
import com.dden.todo.repository.ToDoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebFluxTest(ToDoFluxController.class)
public class ToDoWebFluxTests {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ToDoRepository toDoRepository;

    @Test
    public void testWebFlux() throws Exception{
        given(this.toDoRepository.findAll()).willReturn(
                (Flux<ToDo>) Arrays.asList(new ToDo("Read a Book"), new ToDo("Buy Milk")));

        this.webTestClient.get().uri("/todo-flux").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class);
    }
}
