package com.insurance.calculation.premiumpolicy.controller;

import com.insurance.calculation.premiumpolicy.domain.Policy;
import com.insurance.calculation.premiumpolicy.service.PremiumCalculationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class PremiumCalculatorResource {

    private final PremiumCalculationService calculationService;

    public PremiumCalculatorResource(@Qualifier("premiumCalculationServiceImpl") PremiumCalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping("/premium-calculations")
    public ResponseEntity<String> calculate(@RequestBody Policy policy) {
        BigDecimal premium = calculationService.calculatePremium(policy);
        return new ResponseEntity<>(premium + " EUR", HttpStatus.CREATED);
    }

}
