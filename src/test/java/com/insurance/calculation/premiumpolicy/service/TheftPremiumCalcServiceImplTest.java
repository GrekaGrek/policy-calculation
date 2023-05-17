package com.insurance.calculation.premiumpolicy.service;

import com.insurance.calculation.premiumpolicy.common.PolicyStatusEnum;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.insurance.calculation.premiumpolicy.TestHelper.createPolicy;
import static org.assertj.core.api.Assertions.assertThat;

class TheftPremiumCalcServiceImplTest {

    private final TheftPremiumCalcServiceImpl theftPremiumCalcService = new TheftPremiumCalcServiceImpl();

    @Test
    void calculateTheftPremiumWithLowerCoefficient() {
        var policy = createPolicy(BigDecimal.ZERO, BigDecimal.valueOf(18.25));

        // sum insured >= 15 must be multiplied on 0.05
        var expectedResult = BigDecimal.valueOf(0.91);

        var actualResult = theftPremiumCalcService.calculatePremium(policy);

        assertThat(actualResult.setScale(2, RoundingMode.HALF_UP)).isNotNull().isEqualTo(expectedResult);
    }

    @Test
    void calculateTheftPremiumWithDefaultCoefficient() {
        var policy = createPolicy(BigDecimal.ZERO, BigDecimal.valueOf(11.00));

        // sum insured must be multiplied on default 0.11
        var expectedResult = BigDecimal.valueOf(1.21);

        var actualResult = theftPremiumCalcService.calculatePremium(policy);

        assertThat(actualResult.setScale(2, RoundingMode.HALF_UP)).isNotNull().isEqualTo(expectedResult);
    }

    @Test
    void calculateTheftPremiumWhenSumInsuredIsZero() {
        var policy = createPolicy(BigDecimal.ZERO, BigDecimal.ZERO);

        var expectedResult = BigDecimal.valueOf(0.00);

        var actualResult = theftPremiumCalcService.calculatePremium(policy);

        assertThat(actualResult.setScale(1, RoundingMode.HALF_UP)).isNotNull().isEqualTo(expectedResult);
    }

    @Test
    void calculateTheftPremiumWithDefaultCoefficientWhenPolicyStatusIsDifferent() {
        var policy = createPolicy(BigDecimal.ZERO, BigDecimal.valueOf(11));
                policy.setPolicyStatus(PolicyStatusEnum.APPROVED);

        // sum insured must be multiplied on default 0.11
        var expectedResult = BigDecimal.valueOf(1.21);

        var actualResult = theftPremiumCalcService.calculatePremium(policy);

        assertThat(actualResult.setScale(2, RoundingMode.HALF_UP))
                .isNotNull().isNotEqualByComparingTo(expectedResult);
        assertThat(actualResult.setScale(0, RoundingMode.HALF_UP)).isEqualTo(BigDecimal.ZERO);
    }
}