package com.insurance.calculation.premiumpolicy.domain;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyObject {

    private String objectName;
    private List<PolicySubObject> subObjectList;


}
