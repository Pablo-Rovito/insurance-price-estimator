package com.insurancepriceestimator.backend.factories.policy;

import com.insurancepriceestimator.backend.enums.CoverageEnum;

public class PolicyFactory {
    public static Policy getPolicy(CoverageEnum type) {
        return switch(type) {
            case MINIMUM_COVERAGE -> new MinimumCoveragePolicyImpl();
            case REGULAR_COVERAGE -> new RegularCoveragePolicyImpl();
            case SUPER_COVERAGE -> new SuperCoveragePolicy();
            default -> throw new IllegalArgumentException("invalid coverage type: " + type);
        };
    }
}