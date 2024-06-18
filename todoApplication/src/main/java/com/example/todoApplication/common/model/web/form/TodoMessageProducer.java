package com.example.todoApplication.common.model.web.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoMessageProducer {
    private String userId;
    private String title;
    private String description;
}
