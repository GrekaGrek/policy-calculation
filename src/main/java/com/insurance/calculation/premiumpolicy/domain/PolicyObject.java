package com.insurance.calculation.premiumpolicy.domain;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class PolicyObject {

    private String objectName;
    private List<PolicySubObject> subObjectList;

    public String getObjectName() {
        return objectName;
    }

    public PolicyObject setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }

    public List<PolicySubObject> getSubObjectList() {
        return subObjectList;
    }

    public PolicyObject setSubObjectList(List<PolicySubObject> subObjectList) {
        this.subObjectList = subObjectList;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolicyObject that = (PolicyObject) o;
        return Objects.equals(objectName, that.objectName) && Objects.equals(subObjectList, that.subObjectList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectName, subObjectList);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PolicyObject.class.getSimpleName() + "[", "]")
                .add("objectName=" + objectName)
                .add("subObjectList=" + subObjectList)
                .toString();
    }
}
