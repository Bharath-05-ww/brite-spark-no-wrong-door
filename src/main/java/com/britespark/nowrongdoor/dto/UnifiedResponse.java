package com.britespark.nowrongdoor.dto;

import java.util.List;

public class UnifiedResponse {

    private List<Resident> residents;
    private List<BenefitRecord> benefits;

    private boolean residentSourceAvailable;
    private boolean benefitsSourceAvailable;

    private String benefitsSourceError;

    public UnifiedResponse(
            List<Resident> residents,
            List<BenefitRecord> benefits,
            boolean residentSourceAvailable,
            boolean benefitsSourceAvailable,
            String benefitsSourceError) {

        this.residents = residents;
        this.benefits = benefits;
        this.residentSourceAvailable = residentSourceAvailable;
        this.benefitsSourceAvailable = benefitsSourceAvailable;
        this.benefitsSourceError = benefitsSourceError;
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public List<BenefitRecord> getBenefits() {
        return benefits;
    }

    public boolean isResidentSourceAvailable() {
        return residentSourceAvailable;
    }

    public boolean isBenefitsSourceAvailable() {
        return benefitsSourceAvailable;
    }

    public String getBenefitsSourceError() {
        return benefitsSourceError;
    }
}