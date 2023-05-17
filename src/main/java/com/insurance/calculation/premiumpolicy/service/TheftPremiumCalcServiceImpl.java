package com.insurance.calculation.premiumpolicy.service;

import com.insurance.calculation.premiumpolicy.domain.Policy;
import com.insurance.calculation.premiumpolicy.domain.PolicyObject;
import com.insurance.calculation.premiumpolicy.domain.PolicySubObject;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static com.insurance.calculation.premiumpolicy.common.PolicyStatusEnum.REGISTERED;
import static com.insurance.calculation.premiumpolicy.common.RiskTypeEnum.THEFT;
import static java.util.Optional.ofNullable;

@Service
class TheftPremiumCalcServiceImpl implements PremiumCalculationService {

    private final static BigDecimal LIMIT = BigDecimal.valueOf(15);
    private final static BigDecimal COEFFICIENT_THEFT_DEFAULT = BigDecimal.valueOf(0.11);
    private final static BigDecimal COEFFICIENT_THEFT_OVER_OR_EQUAL_LIMIT = BigDecimal.valueOf(0.05);

    @Override
    public BigDecimal calculatePremium(Policy policy) {
        BigDecimal sumInsuredTheft  = ofNullable(policy)
                .filter(policyStatus -> REGISTERED.equals(policy.getPolicyStatus()))
                .map(Policy::getPolicyObjectList)
                .stream()
                .flatMap(List::stream)
                .map(PolicyObject::getSubObjectList)
                .flatMap(List::stream)
                .filter(Objects::nonNull)
                .filter(policySubObject -> THEFT.equals(policySubObject.getRiskType()))
                .map(PolicySubObject::getSumInsured)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return sumInsuredTheft.compareTo(LIMIT) > 0 ? sumInsuredTheft .multiply(COEFFICIENT_THEFT_OVER_OR_EQUAL_LIMIT)
                : sumInsuredTheft.multiply(COEFFICIENT_THEFT_DEFAULT);
    }
}
