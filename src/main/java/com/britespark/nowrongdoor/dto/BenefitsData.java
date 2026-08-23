package com.britespark.nowrongdoor.dto;

import java.util.List;

public class BenefitsData {

    private boolean available;
    private List<BenefitRecord> records;
    private String error;

    public BenefitsData(
            boolean available,
            List<BenefitRecord> records,
            String error) {

        this.available = available;
        this.records = records;
        this.error = error;
    }

    public boolean isAvailable() {
        return available;
    }

    public List<BenefitRecord> getRecords() {
        return records;
    }

    public String getError() {
        return error;
    }
}