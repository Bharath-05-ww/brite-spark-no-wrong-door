package com.britespark.nowrongdoor.service;

import com.britespark.nowrongdoor.client.BenefitsClient;
import com.britespark.nowrongdoor.client.ResidentClient;
import com.britespark.nowrongdoor.dto.BenefitRecord;
import com.britespark.nowrongdoor.dto.BenefitsResponse;
import com.britespark.nowrongdoor.dto.Resident;
import com.britespark.nowrongdoor.dto.ResidentPage;
import com.britespark.nowrongdoor.dto.SourceResult;
import com.britespark.nowrongdoor.dto.UnifiedResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UnifiedService {

    private final ResidentClient residentClient;
    private final BenefitsClient benefitsClient;

    public UnifiedService(
            ResidentClient residentClient,
            BenefitsClient benefitsClient) {

        this.residentClient = residentClient;
        this.benefitsClient = benefitsClient;
    }

    public UnifiedResponse getUnifiedData() {

        // ---------- REST ----------
        List<Resident> residents = new ArrayList<>();
        Set<String> seenIds = new HashSet<>();

        int pageNumber = 1;
        boolean hasMore = true;

        while (hasMore) {

            ResidentPage page =
                    residentClient.getResidents(pageNumber);

            for (Resident resident : page.getResults()) {

                if (seenIds.add(resident.getId())) {
                    residents.add(resident);
                }
            }

            hasMore = page.isHas_more();
            pageNumber++;
        }

        // ---------- XML ----------
        SourceResult<BenefitsResponse> benefitsResult =
                benefitsClient.getRecords();

        List<BenefitRecord> benefits = new ArrayList<>();

        if (benefitsResult.isAvailable()) {
            benefits = benefitsResult.getData().getRecords();
        }

        // ---------- Combine ----------
        return new UnifiedResponse(
                residents,
                benefits,
                true,
                benefitsResult.isAvailable(),
                benefitsResult.getError()
        );
    }
}