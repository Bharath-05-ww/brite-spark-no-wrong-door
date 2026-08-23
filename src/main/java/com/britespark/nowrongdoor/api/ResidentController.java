package com.britespark.nowrongdoor.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.britespark.nowrongdoor.client.BenefitsClient;
import com.britespark.nowrongdoor.dto.BenefitsResponse;
import com.britespark.nowrongdoor.dto.Resident;
import com.britespark.nowrongdoor.dto.SourceResult;
import com.britespark.nowrongdoor.service.UnifiedService;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

	private final UnifiedService unifiedService;
	private final BenefitsClient benefitsClient;

    public ResidentController(UnifiedService unifiedService, BenefitsClient benefitsClient) {
        this.unifiedService = unifiedService;
        this.benefitsClient = benefitsClient;
    }

    @GetMapping
    public List<Resident> getResidents() {
    	return unifiedService.getAllResidents();

    }

    @GetMapping("/xml-test")
    public SourceResult<BenefitsResponse> testXml() {

        SourceResult<BenefitsResponse> result =
                benefitsClient.getRecords();

        if (result.isAvailable()) {

            System.out.println(
                    "Number of records: " +
                    result.getData().getRecords().size()
            );

            if (!result.getData().getRecords().isEmpty()) {
                System.out.println(
                        "First name: " +
                        result.getData()
                                .getRecords()
                                .get(0)
                                .getName()
                );
            }

        } else {

            System.out.println(
                    "XML service unavailable: " +
                    result.getError()
            );
        }

        return result;
    }
    
}
