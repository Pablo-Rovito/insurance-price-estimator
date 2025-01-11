package com.insurancepriceestimator.backend.model;


public class DiscountResponse {
    private double discountPercentage;
    private double endPrice;

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public DiscountResponse setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
        return this;
    }

    public double getEndPrice() {
        return endPrice;
    }

    public DiscountResponse setEndPrice(double endPrice) {
        this.endPrice = endPrice;
        return this;
    }
}
