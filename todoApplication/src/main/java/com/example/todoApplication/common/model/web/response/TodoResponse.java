package com.example.todoApplication.common.model.web.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TodoResponse implements Serializable {

    private String message;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TodoResponse{");
        sb.append("message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
