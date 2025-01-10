package com.insurancePriceEstimator.backend.enums;

public enum CoverageEnum {
    SUPER_COVERAGE(1000000D, "SUPER_COVERAGE"),
    REGULAR_COVERAGE(500000D, "REGULAR_COVERAGE"),
    MINIMUM_COVERAGE(100000D, "MINIMUM_COVERAGE");

    private final Double amount;
    private final String name;

    CoverageEnum(Double amount, String name) {
        this.amount = amount;
        this.name = name;
    }

    public Double getAmount() {
        return this.amount;
    }

    public String getName() {
        return name;
    }
}