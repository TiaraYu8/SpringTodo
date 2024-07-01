package com.example.todoApplication.mapper;

import com.example.todoApplication.common.model.web.form.TodoMessageProducer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonNodeMapper {

    private final ObjectMapper mapper = new ObjectMapper();

    public JsonNode toJsonNode(TodoMessageProducer producer) {
        return mapper.valueToTree(producer);
    }
}
