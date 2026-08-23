package com.britespark.nowrongdoor.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.britespark.nowrongdoor.dto.BenefitsResponse;
import com.britespark.nowrongdoor.dto.SourceResult;

@Service
public class BenefitsClient {

    private final RestClient restClient;

    public BenefitsClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("http://127.0.0.1:8082")
                .build();
    }

    public SourceResult<BenefitsResponse> getRecords() {

        try {

            BenefitsResponse response = restClient.get()
                    .uri("/records")
                    .retrieve()
                    .body(BenefitsResponse.class);

            return new SourceResult<>(
                    response,
                    true,
                    null
            );

        } catch (Exception e) {

            System.out.println("XML service failed: " + e.getMessage());

            return new SourceResult<>(
                    null,
                    false,
                    e.getMessage()
            );
        }
    }
}
