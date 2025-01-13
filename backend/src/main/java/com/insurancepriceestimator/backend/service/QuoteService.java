package com.insurancepriceestimator.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insurancepriceestimator.backend.entity.Discount;
import com.insurancepriceestimator.backend.entity.Quote;
import com.insurancepriceestimator.backend.factories.policy.Policy;
import com.insurancepriceestimator.backend.factories.policy.PolicyFactory;
import com.insurancepriceestimator.backend.model.QuoteRequest;
import com.insurancepriceestimator.backend.model.QuoteResponse;
import com.insurancepriceestimator.backend.repository.DiscountRepository;
import com.insurancepriceestimator.backend.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import static com.insurancepriceestimator.backend.utils.Utils.*;

@Service
public class QuoteService {

    @Autowired
    QuoteRepository quoteRepository;
    @Autowired
    DiscountRepository discountRepository;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public QuoteService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public QuoteResponse calculateQuote(QuoteRequest request) throws JsonProcessingException {
        printInfo("[QuoteService - calculateQuote] INIT with request: ", request);

        Policy policy = PolicyFactory.getPolicy(request.getCoverage());
        double amount;
        Quote quote = new Quote();

        try {
            Double coverage = request.getCoverage().getAmount();
            printInfo("[QuoteService - calculateQuote] coverage: ", coverage);

            amount = policy.calculatePrice(request.getAge());
            printInfo("[QuoteService - calculateQuote] amount: ", amount);

            quote.setPolicyHolder(request.getName())
                    .setPremium(amount)
                    .setCoverageType(request.getCoverage().getName());
            printInfo("[QuoteService - calculateQuote] quote: ", quote);

            Quote insertedQuote = quoteRepository.save(quote);
            printInfo("[QuoteService - calculateQuote] insertedQuote: ", insertedQuote);

            Discount insertedDiscount = discountRepository.save(calculateDiscount(request, insertedQuote));
            printInfo("[QuoteService - calculateQuote] insertedDiscount: ", insertedDiscount);

            String event = String.format(
                    "New quote: Policy Holder=%s - Coverage Type=%s - Discount Percentage=%.2f - End Price=%.2f",
                    insertedQuote.getPolicyHolder(),
                    insertedQuote.getCoverageType(),
                    insertedDiscount.getDiscountPercentage(),
                    insertedDiscount.getEndPrice()
            );

            kafkaTemplate.send("insurance-quotes", event);

            return mapQuote(insertedQuote, insertedDiscount);

        } catch (Exception e) {
            printInfo("[QuoteService - calculateQuote] ERROR message: ", e.getMessage());
            return new QuoteResponse();
        }
    }
}
