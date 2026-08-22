package com.britespark.nowrongdoor.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.britespark.nowrongdoor.dto.ResidentPage;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class ResidentClient {

    private final RestClient restClient;

    public ResidentClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("http://127.0.0.1:8081")
                .build();
    }

    public ResidentPage getResidents(int page) {
        return restClient.get()
        		.uri("/residents?page=" + page)
                .retrieve()
                .body(ResidentPage.class);

      
    }
}
