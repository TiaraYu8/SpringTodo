package com.example.todoApplication.common.model.web.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class TodoCreate {
    private String userId;
    private String title;
    private String description;
    private MultipartFile cover;
}
