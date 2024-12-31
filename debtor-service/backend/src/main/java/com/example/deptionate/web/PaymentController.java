package com.example.deptionate.web;

import com.example.deptionate.entity.Debt;
import com.example.deptionate.entity.Payment;
import com.example.deptionate.mapper.PaymentMapper;
import com.example.deptionate.model.PaymentDto;
import com.example.deptionate.producer.PaymentProducer;
import com.example.deptionate.service.DebtService;
import com.example.deptionate.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private DebtService debtService;
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private PaymentProducer paymentProducer;

    @PostMapping("/create")
    public ResponseEntity<PaymentDto> create(@RequestBody PaymentDto paymentDto) {
        Optional<Debt> debtById = debtService.findById(paymentDto.getDebtId());
        Payment payment = paymentService.create(paymentMapper.mapDtoToEntity(paymentDto, debtById.get()));
        if (payment != null) {
            producePaymentMessage(payment);
            PaymentDto createdPaymentDto = paymentMapper.mapEntityToDto(payment);
            return new ResponseEntity<>(createdPaymentDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private void producePaymentMessage(Payment payment) {
        if(payment.getAmount().compareTo(payment.getDebt().getAmount()) > 0) {
            throw new RuntimeException("Amount is greater than debt amount");
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String paymentMessage = objectMapper.writeValueAsString(payment);
            paymentProducer.sendPayment(paymentMessage);
        } catch (JsonProcessingException e) {
            Logger logger = LoggerFactory.getLogger(PaymentController.class);
            logger.error("Invalid message content");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> findById(@PathVariable("id") Long id) {
        Optional<Payment> paymentById = paymentService.findById(id);
        if (paymentById.isPresent()) {
            PaymentDto paymentDto = paymentMapper.mapEntityToDto(paymentById.get());
            return new ResponseEntity<>(paymentDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/debt/{id}")
    public List<PaymentDto> findPaymentsByDebtId(@PathVariable("id") Long id) {
        return paymentService.findPaymentsByDebtId(id).stream()
                .map(paymentMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @PutMapping
    public ResponseEntity<PaymentDto> update(@RequestBody PaymentDto paymentDto) {
        Optional<Debt> debtById = debtService.findById(paymentDto.getDebtId());
        Payment payment = paymentMapper.mapDtoToEntity(paymentDto, debtById.get());
        return new ResponseEntity<>(paymentMapper.mapEntityToDto(paymentService.update(payment)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (paymentService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
