package com.insurance.calculation.premiumpolicy.domain;

import com.insurance.calculation.premiumpolicy.common.PolicyStatusEnum;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Policy {

    private String policyNumber;
    private PolicyStatusEnum policyStatus;
    private List<PolicyObject> policyObjectList;
}
