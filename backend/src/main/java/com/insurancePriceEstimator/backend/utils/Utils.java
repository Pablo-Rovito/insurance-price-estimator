package com.insurancePriceEstimator.backend.utils;

public class Utils {
    public static Double calculateRiskFactor(Integer age) {
        if(age < 18) return 0.1D;
        if(age <= 50) return 0.5D;
        return 0.99D;
    }
}
