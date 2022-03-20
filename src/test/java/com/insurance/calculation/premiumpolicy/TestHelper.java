package com.insurance.calculation.premiumpolicy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.insurance.calculation.premiumpolicy.common.PolicyStatusEnum;
import com.insurance.calculation.premiumpolicy.common.RiskTypeEnum;
import com.insurance.calculation.premiumpolicy.domain.Policy;
import com.insurance.calculation.premiumpolicy.domain.PolicyObject;
import com.insurance.calculation.premiumpolicy.domain.PolicySubObject;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

public class TestHelper {

    private static final String POLICY_NUMBER = "LV20-02-100000-5";
    private static final String OBJECT_NAME = "Hotel";
    private static final String SUB_OBJECT_NAME = "10k Xiaomi TV";

    public static Policy createPolicy(BigDecimal sumInsuredFire, BigDecimal sumInsuredTheft) {
        return new Policy()
                .setPolicyNumber(POLICY_NUMBER)
                .setPolicyStatus(PolicyStatusEnum.REGISTERED)
                .setPolicyObjectList(Collections.singletonList(
                                new PolicyObject()
                                        .setObjectName(OBJECT_NAME)
                                        .setSubObjectList(
                                                Arrays.asList(
                                                        new PolicySubObject()
                                                                .setSubObjectName(SUB_OBJECT_NAME)
                                                                .setRiskType(RiskTypeEnum.THEFT)
                                                                .setSumInsured(sumInsuredTheft)
                                                        ,
                                                        new PolicySubObject()
                                                                .setSubObjectName(SUB_OBJECT_NAME)
                                                                .setRiskType(RiskTypeEnum.FIRE)
                                                                .setSumInsured(sumInsuredFire)
                                                )
                                        )
                        )
                );
    }

    public static String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
