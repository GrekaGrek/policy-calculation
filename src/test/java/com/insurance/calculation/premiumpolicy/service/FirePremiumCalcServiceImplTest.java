package com.insurance.calculation.premiumpolicy.service;

import com.insurance.calculation.premiumpolicy.common.PolicyStatusEnum;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.insurance.calculation.premiumpolicy.TestHelper.createPolicy;
import static org.assertj.core.api.Assertions.assertThat;

class FirePremiumCalcServiceImplTest {

    private final FirePremiumCalcServiceImpl firePremiumCalcService = new FirePremiumCalcServiceImpl();

    @Test
    void calculateFirePremiumWhenSumInsuredOverHundredAndCoefficientHigher() {
        var policy = createPolicy(BigDecimal.valueOf(102.00), BigDecimal.ZERO);

        // sum insured > 100 must be multiplied on 0.024
        var expectedResult = BigDecimal.valueOf(2.45);

        var actualResult = firePremiumCalcService.calculatePremium(policy);

        assertThat(actualResult.setScale(2, RoundingMode.HALF_UP)).isNotNull().isEqualTo(expectedResult);
    }

    @Test
    void calculateFirePremiumWhenSumInsuredUnderHundredAndCoefficientDefault() {
        var policy = createPolicy(BigDecimal.valueOf(92.35), BigDecimal.ZERO);

        // sum insured < 100 must be multiplied on default 0.014
        var expectedResult = BigDecimal.valueOf(1.29);

        var actualResult = firePremiumCalcService.calculatePremium(policy);

        assertThat(actualResult.setScale(2, RoundingMode.HALF_UP)).isNotNull().isEqualTo(expectedResult);
    }

    @Test
    void calculateFirePremiumWhenSumInsuredIsZero() {
        var policy = createPolicy(BigDecimal.ZERO, BigDecimal.ZERO);

        var expectedResult = BigDecimal.valueOf(0.00);

        var actualResult = firePremiumCalcService.calculatePremium(policy);

        assertThat(actualResult.setScale(1, RoundingMode.HALF_UP)).isNotNull().isEqualTo(expectedResult);
    }

    @Test
    void calculateFirePremiumWhenSumInsuredOverHundredWhenPolicyStatusIsDifferent() {
        var policy = createPolicy(BigDecimal.valueOf(102.00), BigDecimal.ZERO);
                policy.setPolicyStatus(PolicyStatusEnum.APPROVED);

        // sum insured > 100 must be multiplied on 0.024
        var expectedResult = BigDecimal.valueOf(2.45);

        var actualResult = firePremiumCalcService.calculatePremium(policy);

        assertThat(actualResult.setScale(2, RoundingMode.HALF_UP))
                .isNotNull().isNotEqualByComparingTo(expectedResult);
        assertThat(actualResult.setScale(0, RoundingMode.HALF_UP)).isEqualTo(BigDecimal.ZERO);
    }
}