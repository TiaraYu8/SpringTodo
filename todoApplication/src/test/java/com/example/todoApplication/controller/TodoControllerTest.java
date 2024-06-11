package com.example.todoApplication.controller;

import com.example.todoApplication.TestTodo;
import com.example.todoApplication.common.model.web.form.TodoCreate;
import com.example.todoApplication.common.model.web.form.TodoDelete;
import com.example.todoApplication.common.model.web.form.TodoQuery;
import com.example.todoApplication.common.model.web.form.TodoUpdateStatus;
import com.example.todoApplication.common.model.web.response.*;
import com.example.todoApplication.web.controller.TodoController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TodoControllerTest extends TestTodo {
    private TodoController todoController;

    @Autowired
    public TodoControllerTest(TodoController todoController) {
        this.todoController = todoController;
    }

    @Test
    void createTodoSuccess() throws IOException {

        File file = ResourceUtils.getFile("classpath:test.jpg");
        MockMultipartFile mockFile = new MockMultipartFile("cover", "test.jpg","image/jpeg", Files.readAllBytes(file.toPath()));
        TodoCreate form = new TodoCreate();
        form.setUserId("create id");
        form.setTitle("new name");
        form.setDescription("new desc");
        form.setCover(mockFile);

        ResponseEntity<TodoCreateResponse> response = todoController.createTodo(form, mockFile);
        System.out.println("response: " + response);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("SUCCESS", response.getBody().getMessage());
    }

    @Test
    void findByIdSuccess(){
        TodoQuery form = new TodoQuery();
        form.setId("todo1");

        ResponseEntity<TodoGetResponse> response = todoController.getTodo(form);
        System.out.println("response: " + response);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("SUCCESS", response.getBody().getMessage());

    }

    @Test
    void findAllSuccess(){
        ResponseEntity<TodoQueryListResponse> response = todoController.getAllTodo();
        System.out.println("response: " + response);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("SUCCESS", response.getBody().getMessage());

    }

    @Test
    void updateStatusSuccess(){
        TodoUpdateStatus form = new TodoUpdateStatus();
        form.setTodoId("todo1");
        form.setUserId("user1");
        form.setIsFinished("selesai");

        ResponseEntity<TodoUpdateStatusResponse> response = todoController.updateTodo(form);
        System.out.println("response: " + response);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("SUCCESS", response.getBody().getMessage());

    }
    @Test
    void updateStatusFail(){
        TodoUpdateStatus form = new TodoUpdateStatus();
        form.setTodoId("todo not found");
        form.setUserId("user1");
        form.setIsFinished("selesai");

        ResponseEntity<TodoUpdateStatusResponse> response = todoController.updateTodo(form);
        System.out.println("response: " + response);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("SUCCESS", response.getBody().getMessage());

    }
    @Test
    void deleteSuccess(){
        TodoDelete form = new TodoDelete();
        form.setId("todo1");

        ResponseEntity<TodoDeleteResponse> response = todoController.deleteTodo(form);
        System.out.println("response: " + response);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("SUCCESS", response.getBody().getMessage());

    }
}
