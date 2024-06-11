package com.example.todoApplication.integration;

import com.example.todoApplication.common.model.client.request.FeatureManagementRequest;
import com.example.todoApplication.common.model.client.result.FeatureManagamentResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "restfiturmanagement", url = "${spring.application.restfiturmanagement}", path="/feature/manage")
public interface FeatureManagementWebClient {
    @PostMapping("/find/list")
    ResponseEntity<FeatureManagamentResult> findFeatureManagement(FeatureManagementRequest request);
}
