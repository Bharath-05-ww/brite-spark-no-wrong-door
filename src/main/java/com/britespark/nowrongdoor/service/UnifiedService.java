package com.britespark.nowrongdoor.service;

import com.britespark.nowrongdoor.client.ResidentClient;
import com.britespark.nowrongdoor.dto.Resident;
import com.britespark.nowrongdoor.dto.ResidentPage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UnifiedService {

    private final ResidentClient residentClient;

    public UnifiedService(ResidentClient residentClient) {
        this.residentClient = residentClient;
    }

    public List<Resident> getAllResidents() {

        List<Resident> residents = new ArrayList<>();
        Set<String> seenIds = new HashSet<>();

        int pageNumber = 1;
        boolean hasMore = true;
        int totalFetched = 0;
        while (hasMore) {

            ResidentPage page = residentClient.getResidents(pageNumber);
            totalFetched += page.getResults().size();

            for (Resident resident : page.getResults()) {

                if (seenIds.add(resident.getId())) {
                    residents.add(resident);
                }
            }

            hasMore = page.isHas_more();
            pageNumber++;
        }
        System.out.println("Total fetched: " + totalFetched);
        System.out.println("Unique residents: " + residents.size());

        return residents;
    }
}