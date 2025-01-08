package com.example.deptionate.messaging;

import com.example.deptionate.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    @Value("${kafka.topic.debt-payments}")
    private String topicName;

    public PaymentProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPayment(Payment payment) {
        Message<Payment> message = MessageBuilder
                .withPayload(payment)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();
        kafkaTemplate.send(message);
    }
}
