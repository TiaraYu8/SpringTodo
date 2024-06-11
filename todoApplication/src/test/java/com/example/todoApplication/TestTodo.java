package com.example.todoApplication;

import com.example.todoApplication.configuration.TodoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@ActiveProfiles("unittest")
@Sql(scripts = "classpath:/local-db.sql")
@SpringBootTest(classes = TodoConfiguration.class)
public class TestTodo {
    @Test
    void loadContext(){

    }
}
