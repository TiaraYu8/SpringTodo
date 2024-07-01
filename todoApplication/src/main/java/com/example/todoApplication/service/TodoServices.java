package com.example.todoApplication.service;

//import com.example.todoApplication.common.model.database.UserModel;
import com.example.todoApplication.common.model.database.UserModel;
import com.example.todoApplication.common.model.viewobject.TodoVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TodoServices {
    TodoVO findTodoById(String id);
    List<TodoVO> findAll();
    String createTodo(UserModel userId, String title, String description, MultipartFile cover);
    void isFinished (String userId, String id, String isFinished);
    void deleteTodo(String id);
    void init();
    String saveImage(MultipartFile file);
}
