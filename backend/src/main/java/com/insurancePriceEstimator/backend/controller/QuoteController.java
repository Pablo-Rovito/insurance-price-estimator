package com.insurancePriceEstimator.backend.controller;

import com.insurancePriceEstimator.backend.entity.Quote;
import com.insurancePriceEstimator.backend.model.QuoteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.insurancePriceEstimator.backend.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/quotes")
public class QuoteController {
    @Autowired
    QuoteService srv;

    @PostMapping
    public ResponseEntity<Quote> createQuote(@RequestBody QuoteRequest request) {
        Quote quote = srv.calculateQuote(request);
        return ResponseEntity.ok(quote);
    }
}
