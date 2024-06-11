package com.example.todoApplication.common.model.web.response;

import com.example.todoApplication.common.model.viewobject.TodoVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoGetResponse extends TodoResponse{
    private TodoVO todo;
}
