package com.example.todoApplication.common.model.web.response;

import com.example.todoApplication.common.model.viewobject.TodoVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TodoQueryListResponse extends TodoResponse {
    private List<TodoVO> todos;
}
