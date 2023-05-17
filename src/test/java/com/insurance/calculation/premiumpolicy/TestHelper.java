package com.insurance.calculation.premiumpolicy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurance.calculation.premiumpolicy.common.PolicyStatusEnum;
import com.insurance.calculation.premiumpolicy.common.RiskTypeEnum;
import com.insurance.calculation.premiumpolicy.domain.Policy;
import com.insurance.calculation.premiumpolicy.domain.PolicyObject;
import com.insurance.calculation.premiumpolicy.domain.PolicySubObject;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class TestHelper {

    private static final String POLICY_NUMBER = "LV20-02-100000-5";
    private static final String OBJECT_NAME = "Hotel";
    private static final String SUB_OBJECT_NAME = "10k Xiaomi TV";

    public static Policy createPolicy(BigDecimal sumInsuredFire, BigDecimal sumInsuredTheft) {
        return new Policy(
                POLICY_NUMBER,
                PolicyStatusEnum.REGISTERED,
                Collections.singletonList(
                        new PolicyObject(
                                OBJECT_NAME,
                                List.of(new PolicySubObject(
                                                SUB_OBJECT_NAME,
                                                sumInsuredTheft,
                                                RiskTypeEnum.THEFT
                                        ),
                                        new PolicySubObject(
                                                SUB_OBJECT_NAME,
                                                sumInsuredFire,
                                                RiskTypeEnum.FIRE
                                        ))
                        )));
    }

    public static String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
