package com.insurancepriceestimator.backend.model;

public class QuoteResponse {
    private Long id;
    private String policyHolder;
    private Double premium;
    private String coverageType;
    private DiscountResponse discount;

    public Long getId() {
        return id;
    }

    public QuoteResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPolicyHolder() {
        return policyHolder;
    }

    public QuoteResponse setPolicyHolder(String policyHolder) {
        this.policyHolder = policyHolder;
        return this;
    }

    public Double getPremium() {
        return premium;
    }

    public QuoteResponse setPremium(Double premium) {
        this.premium = premium;
        return this;
    }

    public String getCoverageType() {
        return coverageType;
    }

    public QuoteResponse setCoverageType(String coverageType) {
        this.coverageType = coverageType;
        return this;
    }

    public DiscountResponse getDiscount() {
        return discount;
    }

    public QuoteResponse setDiscount(DiscountResponse discount) {
        this.discount = discount;
        return this;
    }
}
