package com.insurancePriceEstimator.backend.service;

import com.insurancePriceEstimator.backend.entity.Quote;
import com.insurancePriceEstimator.backend.model.QuoteRequest;
import com.insurancePriceEstimator.backend.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.insurancePriceEstimator.backend.utils.Utils.calculateRiskFactor;

@Service
public class QuoteService {

    @Autowired
    QuoteRepository repository;

    public Quote calculateQuote(QuoteRequest request) {

        double amount;
        Quote quote = new Quote();

        try {
            Double coverage = request.getCoverage().getAmount();
            amount = coverage * request.getAge() * calculateRiskFactor(request.getAge());
            quote.setPolicyHolder(request.getName())
                    .setPremium(amount)
                    .setCoverageType(request.getCoverage().getName());
            repository.save(quote);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return quote;
    }
}
