package com.insurancepriceestimator.backend.model;

import com.insurancepriceestimator.backend.enums.CoverageEnum;

public class QuoteRequest {
    private Integer age;
    private String name;
    private CoverageEnum coverage;

    public Integer getAge() {
        return age;
    }

    public QuoteRequest setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getName() {
        return name;
    }

    public QuoteRequest setName(String name) {
        this.name = name;
        return this;
    }

    public CoverageEnum getCoverage() {
        return coverage;
    }

    public QuoteRequest setCoverage(CoverageEnum coverage) {
        this.coverage = coverage;
        return this;
    }
}
