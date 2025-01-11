package com.insurancepriceestimator.backend.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurancepriceestimator.backend.entity.Discount;
import com.insurancepriceestimator.backend.entity.Quote;
import com.insurancepriceestimator.backend.model.QuoteRequest;

public class Utils {
    public static Double calculateRiskFactor(Integer age) {
        if(age < 18) return 0.1D;
        if(age <= 50) return 0.5D;
        return 0.99D;
    }

    public static void printInfo(String message, Object... objs) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        StringBuilder messageBuilder = new StringBuilder(message);
        for(Object obj : objs) {
            messageBuilder.append(" | ").append(mapper.writeValueAsString(obj));
        }
        message = messageBuilder.toString();
        System.out.println(message);
    }

    public static Discount calculateDiscount(QuoteRequest request, Quote quote) {
        Discount discount = new Discount();
        if(request.getAge() <= 25) discount.setDiscountPercentage(.25D);
        if(request.getName().contains("discount")) discount.setDiscountPercentage(discount.getDiscountPercentage() + .1D);
        discount.setEndPrice(quote.getPremium() * (1D - discount.getDiscountPercentage()));

        return discount;
    }
}
