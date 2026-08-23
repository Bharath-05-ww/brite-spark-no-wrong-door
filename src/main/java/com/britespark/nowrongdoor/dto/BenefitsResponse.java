package com.britespark.nowrongdoor.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class BenefitsResponse {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Record")
    private List<BenefitRecord> records;

    public List<BenefitRecord> getRecords() {
        return records;
    }

    public void setRecords(List<BenefitRecord> records) {
        this.records = records;
    }
}
