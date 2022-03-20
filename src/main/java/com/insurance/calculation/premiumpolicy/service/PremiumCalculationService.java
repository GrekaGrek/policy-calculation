package com.insurance.calculation.premiumpolicy.service;

import com.insurance.calculation.premiumpolicy.domain.Policy;

import java.math.BigDecimal;

public interface PremiumCalculationService {
    BigDecimal calculatePremium(Policy policy);
}
