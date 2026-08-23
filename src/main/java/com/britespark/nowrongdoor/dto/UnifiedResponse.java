package com.britespark.nowrongdoor.dto;

import java.util.List;

public class UnifiedResponse {

    private List<Resident> residents;
    private BenefitsData benefits;

    private boolean residentSourceAvailable;

    public UnifiedResponse(
            List<Resident> residents,
            BenefitsData benefits,
            boolean residentSourceAvailable) {

        this.residents = residents;
        this.benefits = benefits;
        this.residentSourceAvailable = residentSourceAvailable;
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public BenefitsData getBenefits() {
        return benefits;
    }

    public boolean isResidentSourceAvailable() {
        return residentSourceAvailable;
    }
}