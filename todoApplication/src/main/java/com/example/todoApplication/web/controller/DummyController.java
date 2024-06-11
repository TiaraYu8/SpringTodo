package com.example.todoApplication.web.controller;

import com.example.todoApplication.common.model.client.request.FeatureManagementRequest;
import com.example.todoApplication.common.model.client.result.FeatureManagamentResult;
import com.example.todoApplication.common.model.web.form.TodoCreate;
import com.example.todoApplication.integration.FeatureManagementWebClient;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/dummy")
public class DummyController {
    private FeatureManagementWebClient featureManagamentWebClient;

    @Autowired
    public DummyController(FeatureManagementWebClient featureManagamentWebClient) {
        this.featureManagamentWebClient = featureManagamentWebClient;
    }
    @PostMapping("/test")
    public ResponseEntity<FeatureManagamentResult> createTodo(){
        FeatureManagementRequest request = new FeatureManagementRequest();
        request.setPage(1);
        return ResponseEntity.ok(featureManagamentWebClient.findFeatureManagement(request).getBody());
    }
}
