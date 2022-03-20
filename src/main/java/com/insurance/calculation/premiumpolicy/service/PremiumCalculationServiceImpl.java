package com.insurance.calculation.premiumpolicy.service;

import com.insurance.calculation.premiumpolicy.domain.Policy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PremiumCalculationServiceImpl implements PremiumCalculationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PremiumCalculationServiceImpl.class);

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
        LOGGER.debug("Total sum insured for all policy's sub-objects with type Fire = {}", sumInsuredFire);

        BigDecimal sumInsuredTheft = theftPremiumCalcService.calculatePremium(policy);
        LOGGER.debug("Total sum insured for all policy's sub-objects with type Theft = {}", sumInsuredTheft);

        return sumInsuredFire.add(sumInsuredTheft).setScale(2, RoundingMode.HALF_UP);
    }
}
