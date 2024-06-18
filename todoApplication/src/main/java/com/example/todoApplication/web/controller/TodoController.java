package com.example.todoApplication.web.controller;

import com.example.todoApplication.common.model.database.UserModel;
import com.example.todoApplication.common.model.viewobject.TodoVO;
import com.example.todoApplication.common.model.web.form.TodoCreate;
import com.example.todoApplication.common.model.web.form.TodoDelete;
import com.example.todoApplication.common.model.web.form.TodoQuery;
import com.example.todoApplication.common.model.web.form.TodoUpdateStatus;
import com.example.todoApplication.common.model.web.response.*;
import com.example.todoApplication.service.TodoServices;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
@RequestMapping("/todo")
public class TodoController {
    public static final Logger logger = LoggerFactory.getLogger("COMMON_ERROR");

    private final TodoServices todoServices;

    @Autowired
        public TodoController(TodoServices todoServices) {

        this.todoServices = todoServices;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<TodoCreateResponse> createTodo(@Valid @ModelAttribute TodoCreate form, @RequestParam("cover")MultipartFile file){
        try{
            todoServices.saveImage(file);
            UserModel user = new UserModel();
            String todoId = todoServices.createTodo(
                    user,
                    form.getTitle(),
                    form.getDescription(),
                    file);
            TodoCreateResponse res = new TodoCreateResponse();
            res.setId(todoId);
            res.setMessage("SUCCESS");

            return ResponseEntity.ok(res);
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            TodoCreateResponse res = new TodoCreateResponse();
            res.setMessage(e.getMessage());

            return ResponseEntity.internalServerError().body(res);

        }
    }

    @PostMapping("/find")
    public ResponseEntity<TodoGetResponse> getTodo(@RequestBody TodoQuery form){
        try{
            TodoVO todoVO = todoServices.findTodoById(form.getId());
            TodoGetResponse response = new TodoGetResponse();
            response.setTodo(todoVO);
            response.setMessage("SUCCESS");

            return ResponseEntity.ok(response);
        } catch(Exception e){
            logger.error(e.getMessage(), e);
            TodoGetResponse res = new TodoGetResponse();
            res.setMessage(e.getMessage());

            return ResponseEntity.internalServerError().body(res);

        }
    }

    @PostMapping("/find/all")
    public ResponseEntity<TodoQueryListResponse> getAllTodo(){
        try{
            List<TodoVO> todoVO = todoServices.findAll();
            TodoQueryListResponse response = new TodoQueryListResponse();
            response.setTodos(todoVO);
            response.setMessage("SUCCESS");

            return ResponseEntity.ok(response);
        } catch(Exception e){
            logger.error(e.getMessage(), e);
            TodoQueryListResponse res = new TodoQueryListResponse();
            res.setMessage(e.getMessage());

            return ResponseEntity.internalServerError().body(res);

        }
    }

    @PostMapping(value = "/status")
    public ResponseEntity<TodoUpdateStatusResponse> updateTodo(@RequestBody TodoUpdateStatus form){
            try{
            todoServices.isFinished(
                    form.getUserId(),
                    form.getTodoId(),
                    form.getIsFinished());
            TodoUpdateStatusResponse res = new TodoUpdateStatusResponse();
            res.setMessage("SUCCESS");

            return ResponseEntity.ok(res);
        } catch (Exception e){
            TodoUpdateStatusResponse res = new TodoUpdateStatusResponse();
            res.setMessage(e.getMessage());

            return ResponseEntity.internalServerError().body(res);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<TodoDeleteResponse> deleteTodo(@RequestBody TodoDelete form){
        try{
            todoServices.deleteTodo(form.getId());
            TodoDeleteResponse response = new TodoDeleteResponse();
            response.setMessage("SUCCESS");

            return ResponseEntity.ok(response);
        } catch(Exception e){
            logger.error(e.getMessage(), e);
            TodoDeleteResponse res = new TodoDeleteResponse();
            res.setMessage(e.getMessage());

            return ResponseEntity.internalServerError().body(res);

        }
    }
}
