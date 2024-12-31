package com.example.deptionate.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {
    @Value("${kafka.topic.debt-payments}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public PaymentProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPayment(String paymentMessage) {
        kafkaTemplate.send(topicName, paymentMessage);
    }
}
