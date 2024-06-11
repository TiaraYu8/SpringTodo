package com.example.todoApplication.common.model.web.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoUpdateStatus {
    private String todoId;
    private String userId;

    @JsonProperty("is_finished")
    private String isFinished;
}
