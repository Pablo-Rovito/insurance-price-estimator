package com.insurancepriceestimator.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insurancepriceestimator.backend.model.QuoteRequest;
import com.insurancepriceestimator.backend.model.QuoteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.insurancepriceestimator.backend.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/quotes")
public class QuoteController {
    @Autowired
    QuoteService srv;

    @PostMapping
    public ResponseEntity<QuoteResponse> createQuote(@RequestBody QuoteRequest request) throws JsonProcessingException {
        QuoteResponse quote = srv.calculateQuote(request);
        if(quote.getId() == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(quote);
    }
}