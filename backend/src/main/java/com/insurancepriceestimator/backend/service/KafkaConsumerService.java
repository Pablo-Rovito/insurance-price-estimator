package com.insurancepriceestimator.backend.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "insurance-quotes", groupId = "console-printer-group")
    public void consume(String message) {
        System.out.println("Message received: " + message);
    }
}
