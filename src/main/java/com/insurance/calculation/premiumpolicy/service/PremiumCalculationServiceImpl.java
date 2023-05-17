package com.insurance.calculation.premiumpolicy.service;

import com.insurance.calculation.premiumpolicy.domain.Policy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
public class PremiumCalculationServiceImpl implements PremiumCalculationService {

    private final PremiumCalculationService firePremiumCalcService;
    private final PremiumCalculationService theftPremiumCalcService;

    public PremiumCalculationServiceImpl(@Qualifier("firePremiumCalcServiceImpl") PremiumCalculationService firePremiumCalcService,
                                         @Qualifier("theftPremiumCalcServiceImpl") PremiumCalculationService theftPremiumCalcService) {
        this.firePremiumCalcService = firePremiumCalcService;
        this.theftPremiumCalcService = theftPremiumCalcService;
    }

    @Override
    public BigDecimal calculatePremium(Policy policy) {
        BigDecimal sumInsuredFire = firePremiumCalcService.calculatePremium(policy);
        log.debug("Total sum insured for all policy's sub-objects with type Fire = {}", sumInsuredFire);

        BigDecimal sumInsuredTheft = theftPremiumCalcService.calculatePremium(policy);
        log.debug("Total sum insured for all policy's sub-objects with type Theft = {}", sumInsuredTheft);

        return sumInsuredFire.add(sumInsuredTheft).setScale(2, RoundingMode.HALF_UP);
    }
}
