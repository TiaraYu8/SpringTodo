package com.example.todoApplication.common.model.client.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FeatureManagamentResult {
    private Boolean success;
    private String responseCode;
    private String responseMessage;
    private Boolean hashNext;
}
