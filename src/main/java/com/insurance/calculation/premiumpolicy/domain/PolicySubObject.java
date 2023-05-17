package com.insurance.calculation.premiumpolicy.domain;

import com.insurance.calculation.premiumpolicy.common.RiskTypeEnum;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicySubObject {

    private String subObjectName;
    private BigDecimal sumInsured;
    private RiskTypeEnum riskType;
}
