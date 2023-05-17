package com.insurance.calculation.premiumpolicy.service;

import com.insurance.calculation.premiumpolicy.domain.Policy;
import com.insurance.calculation.premiumpolicy.domain.PolicyObject;
import com.insurance.calculation.premiumpolicy.domain.PolicySubObject;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static com.insurance.calculation.premiumpolicy.common.PolicyStatusEnum.REGISTERED;
import static com.insurance.calculation.premiumpolicy.common.RiskTypeEnum.FIRE;
import static java.util.Optional.ofNullable;

@Service
class FirePremiumCalcServiceImpl implements PremiumCalculationService {

    private final static BigDecimal LIMIT = BigDecimal.valueOf(100);
    private final static BigDecimal COEFFICIENT_FIRE_DEFAULT = BigDecimal.valueOf(0.014);
    private final static BigDecimal COEFFICIENT_FIRE_OVER_LIMIT = BigDecimal.valueOf(0.024);

    @Override
    public BigDecimal calculatePremium(Policy policy) {
        BigDecimal sumInsuredFire = ofNullable(policy)
                .filter(policyStatus -> REGISTERED.equals(policy.getPolicyStatus()))
                .map(Policy::getPolicyObjectList)
                .stream()
                .flatMap(List::stream)
                .map(PolicyObject::getSubObjectList)
                .flatMap(List::stream)
                .filter(Objects::nonNull)
                .filter(policySubObject -> FIRE.equals(policySubObject.getRiskType()))
                .map(PolicySubObject::getSumInsured)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return sumInsuredFire.compareTo(LIMIT) > 0 ? sumInsuredFire.multiply(COEFFICIENT_FIRE_OVER_LIMIT)
                : sumInsuredFire.multiply(COEFFICIENT_FIRE_DEFAULT);
    }
}
