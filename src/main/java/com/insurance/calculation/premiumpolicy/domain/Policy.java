package com.insurance.calculation.premiumpolicy.domain;

import com.insurance.calculation.premiumpolicy.common.PolicyStatusEnum;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Policy {

    private String policyNumber;
    private PolicyStatusEnum policyStatus;
    private List<PolicyObject> policyObjectList;

    public String getPolicyNumber() {
        return policyNumber;
    }

    public Policy setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
        return this;
    }

    public PolicyStatusEnum getPolicyStatus() {
        return policyStatus;
    }

    public Policy setPolicyStatus(PolicyStatusEnum policyStatus) {
        this.policyStatus = policyStatus;
        return this;
    }

    public List<PolicyObject> getPolicyObjectList() {
        return policyObjectList;
    }

    public Policy setPolicyObjectList(List<PolicyObject> policyObjectList) {
        this.policyObjectList = policyObjectList;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Policy policy = (Policy) o;
        return Objects.equals(policyNumber, policy.policyNumber) && policyStatus == policy.policyStatus
                && Objects.equals(policyObjectList, policy.policyObjectList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNumber, policyStatus, policyObjectList);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Policy.class.getSimpleName() + "[", "]")
                .add("policyNumber=" + policyNumber)
                .add("policyStatus=" + policyStatus)
                .add("policyObjectList=" + policyObjectList)
                .toString();
    }
}
