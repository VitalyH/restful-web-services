package com.vitalyh.restfulwebservices.Filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

//Another way to do static filtering but with hardcoding
// @JsonIgnoreProperties(value={"field1", "field2"})
// Dynamic filtering
@JsonFilter("SomeBeanFilter")
public class SomeBean {

    private String field1;
    private String field2;

    //Static filtering
    //@JsonIgnore
    private String field3;

    public SomeBean(String value1, String value2, String value3) {
        this.field1 = value1;
        this.field2 = value2;
        this.field3 = value3;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }
}
