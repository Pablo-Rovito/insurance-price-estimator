package com.insurancepriceestimator.backend.factories.policy;

import com.insurancepriceestimator.backend.enums.CoverageEnum;

import static com.insurancepriceestimator.backend.utils.Utils.calculateRiskFactor;

public class MinimumCoveragePolicyImpl implements Policy {
    @Override
    public double calculatePrice(int age) {
        return CoverageEnum.MINIMUM_COVERAGE.getAmount() * age * calculateRiskFactor(age) * 1.2;
    }
}
