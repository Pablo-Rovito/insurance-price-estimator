package com.insurancepriceestimator.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insurancepriceestimator.backend.entity.Discount;
import com.insurancepriceestimator.backend.entity.Quote;
import com.insurancepriceestimator.backend.model.QuoteRequest;
import com.insurancepriceestimator.backend.model.QuoteResponse;
import com.insurancepriceestimator.backend.repository.DiscountRepository;
import com.insurancepriceestimator.backend.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.insurancepriceestimator.backend.utils.Utils.*;

@Service
public class QuoteService {

    @Autowired
    QuoteRepository quoteRepository;
    @Autowired
    DiscountRepository discountRepository;

    public QuoteResponse calculateQuote(QuoteRequest request) throws JsonProcessingException {
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
                    .setCoverageType(request.getCoverage().getName());
            printInfo("[QuoteService - calculateQuote] quote: ", quote);

            Quote insertedQuote = quoteRepository.save(quote);
            printInfo("[QuoteService - calculateQuote] insertedQuote: ", insertedQuote);

            Discount insertedDiscount = discountRepository.save(calculateDiscount(request, insertedQuote));
            printInfo("[QuinoteService - calculateQuote] insertedDiscount: ", insertedDiscount);

            return mapQuote(insertedQuote, insertedDiscount);

        } catch (Exception e) {
            printInfo("[QuoteService - calculateQuote] ERROR message: ", e.getMessage());
            return new QuoteResponse();
        }
    }
}
