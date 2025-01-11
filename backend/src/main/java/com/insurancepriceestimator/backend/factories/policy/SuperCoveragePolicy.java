package com.insurancepriceestimator.backend.factories.policy;

import com.insurancepriceestimator.backend.enums.CoverageEnum;

import static com.insurancepriceestimator.backend.utils.Utils.calculateRiskFactor;

public class SuperCoveragePolicy implements Policy {
    @Override
    public double calculatePrice(int age) {
        return CoverageEnum.SUPER_COVERAGE.getAmount() * age * calculateRiskFactor(age) * 0.8;
    }
}
