package com.example.todoApplication.common.model.message;

import com.example.todoApplication.common.model.consts.KafkaTopicConsts;
import com.example.todoApplication.common.model.web.form.TodoMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TodoMessage {
    private KafkaTemplate<String, TodoMessageProducer> kafkaTemplate;

    public void publish(TodoMessageProducer message) {
        kafkaTemplate.send(KafkaTopicConsts.TOPIC_TRANSACTION, message);
    }

    @Autowired
    public void setKafkaTemplate(KafkaTemplate<String, TodoMessageProducer> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
}
