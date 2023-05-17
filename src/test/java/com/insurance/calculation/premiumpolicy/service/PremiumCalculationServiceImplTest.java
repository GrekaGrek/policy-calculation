package com.insurance.calculation.premiumpolicy.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.insurance.calculation.premiumpolicy.TestHelper.createPolicy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PremiumCalculationServiceImplTest {

    @Mock
    private PremiumCalculationService mockCalculationService;

    @InjectMocks
    private PremiumCalculationServiceImpl premiumCalculationService;

    @Test
    void calculateFireAndTheftPremiumSum() {
        var sumInsuredFire = BigDecimal.valueOf(100.00);
        var sumInsuredTheft = BigDecimal.valueOf(8.00);
        var policy = createPolicy(sumInsuredFire, sumInsuredTheft);

        when(mockCalculationService.calculatePremium(policy)).thenReturn(sumInsuredFire).thenReturn(sumInsuredTheft);

        var actualResult = premiumCalculationService.calculatePremium(policy);

        assertThat(actualResult)
                .isNotNull()
                .isEqualTo(BigDecimal.valueOf(108.00).setScale(2, RoundingMode.HALF_UP));

        verify(mockCalculationService, times(2)).calculatePremium(policy);
    }
}