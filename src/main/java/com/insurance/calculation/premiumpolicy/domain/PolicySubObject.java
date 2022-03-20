package com.insurance.calculation.premiumpolicy.domain;

import com.insurance.calculation.premiumpolicy.common.RiskTypeEnum;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class PolicySubObject {

    private String subObjectName;
    private BigDecimal sumInsured;
    private RiskTypeEnum riskType;

    public String getSubObjectName() {
        return subObjectName;
    }

    public PolicySubObject setSubObjectName(String subObjectName) {
        this.subObjectName = subObjectName;
        return this;
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    public PolicySubObject setSumInsured(BigDecimal sumInsured) {
        this.sumInsured = sumInsured;
        return this;
    }

    public RiskTypeEnum getRiskType() {
        return riskType;
    }

    public PolicySubObject setRiskType(RiskTypeEnum riskType) {
        this.riskType = riskType;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolicySubObject that = (PolicySubObject) o;
        return Objects.equals(subObjectName, that.subObjectName) && Objects.equals(sumInsured, that.sumInsured)
                && riskType == that.riskType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subObjectName, sumInsured, riskType);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PolicySubObject.class.getSimpleName() + "[", "]")
                .add("subObjectName=" + subObjectName)
                .add("sumInsured=" + sumInsured)
                .add("riskType=" + riskType)
                .toString();
    }
}
