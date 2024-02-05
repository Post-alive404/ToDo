package com.dden.todo;

import com.dden.todo.domain.ToDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ToDoMongoTests {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void toDoMongoTest(){
        ToDo toDo = new ToDo("Read");
        this.mongoTemplate.save(toDo);

        ToDo result = this.mongoTemplate.findById(toDo.getId(), ToDo.class);
        assertThat(result.getId()).isEqualTo(toDo.getId());
    }
}
