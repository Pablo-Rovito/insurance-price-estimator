package com.insurancepriceestimator.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insurancepriceestimator.backend.entity.Quote;
import com.insurancepriceestimator.backend.model.QuoteRequest;
import com.insurancepriceestimator.backend.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.insurancepriceestimator.backend.utils.Utils.*;

@Service
public class QuoteService {

    @Autowired
    QuoteRepository repository;

    public Quote calculateQuote(QuoteRequest request) throws JsonProcessingException {
        printInfo("[QuoteService - calculateQuote] INIT with request: ", request);

        double amount;
        Quote quote = new Quote();

        try {
            Double coverage = request.getCoverage().getAmount();
            printInfo("[QuoteService - calculateQuote] coverage: ", coverage);

            amount = coverage * request.getAge() * calculateRiskFactor(request.getAge());
            printInfo("[QuoteService - calculateQuote] amount: ", amount);

            quote.setPolicyHolder(request.getName())
                    .setPremium(amount)
                    .setCoverageType(request.getCoverage().getName())
                    .setDiscount(calculateDiscount(request, quote));
            printInfo("[QuoteService - calculateQuote] quote: ", quote);

            repository.save(quote);
        } catch (Exception e) {
            printInfo("[QuoteService - calculateQuote] ERROR message: ", e.getMessage());
            return new Quote();
        }

        return quote;
    }
}
