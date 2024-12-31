package com.deptionate.creditorservice.consumer;

import com.deptionate.creditorservice.dao.DebtPaymentRepository;
import com.deptionate.creditorservice.mapper.DebtPaymentMapper;
import com.deptionate.creditorservice.entity.DebtPayment;
import com.deptionate.creditorservice.model.Payment;
import com.deptionate.creditorservice.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PaymentKafkaConsumer {
    private final ObjectMapper objectMapper;

    @Autowired
    private DebtPaymentMapper debtPaymentMapper;
    @Autowired
    private DebtPaymentRepository debtPaymentRepository;
    @Qualifier("webClientBuilder")
    @Autowired
    private WebClient.Builder webClientBuilder;

    public PaymentKafkaConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "payments", groupId = "creditor-group")
    public void consume(String message) {
        System.out.println(message);

        try {
            Payment payment = objectMapper.readValue(message, Payment.class);
            DebtPayment debtPayment = debtPaymentMapper.mapEntityFromPayment(payment);
            debtPaymentRepository.save(debtPayment);

            webClientBuilder.build()
                    .put()
                    .uri("http://localhost:8080/api/debts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(new Response(debtPayment, payment)), Response.class)
                    .retrieve()
                    .toBodilessEntity()  // Не обработваме тялото на отговора
                    .doOnSuccess(response -> System.out.println("Request successful with status: " + response.getStatusCode()))
                    .doOnError(error -> System.err.println("Error occurred: " + error.getMessage()))
                    .subscribe();
        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
        }
    }
}
