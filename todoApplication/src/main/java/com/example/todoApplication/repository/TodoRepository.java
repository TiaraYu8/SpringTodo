package com.example.todoApplication.repository;

import com.example.todoApplication.common.model.database.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoModel,String> {
}
