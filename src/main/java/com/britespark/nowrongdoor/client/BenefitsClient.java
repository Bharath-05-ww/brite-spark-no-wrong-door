package com.britespark.nowrongdoor.client;

import java.time.Duration;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import com.britespark.nowrongdoor.dto.BenefitsResponse;
import com.britespark.nowrongdoor.dto.SourceResult;

@Service
public class BenefitsClient {

    private final RestClient restClient;

    public BenefitsClient(RestClient.Builder restClientBuilder) {

        SimpleClientHttpRequestFactory requestFactory =
                new SimpleClientHttpRequestFactory();

        requestFactory.setConnectTimeout(Duration.ofSeconds(1));
        requestFactory.setReadTimeout(Duration.ofSeconds(3));

        this.restClient = restClientBuilder
                .baseUrl("http://127.0.0.1:8082")
                .requestFactory(requestFactory)
                .build();
    }
    private void sleepBeforeRetry() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public SourceResult<BenefitsResponse> getRecords() {

        int maxAttempts = 3;
        Exception lastException = null;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {

            try {

                BenefitsResponse response = restClient.get()
                        .uri("/records")
                        .retrieve()
                        .body(BenefitsResponse.class);

                System.out.println(
                        "XML request succeeded on attempt "
                                + attempt
                );

                return new SourceResult<>(
                        response,
                        true,
                        null
                );

            } catch (HttpServerErrorException e) {

                lastException = e;

                System.out.println(
                        "XML server error on attempt "
                                + attempt
                                + ": "
                                + e.getMessage()
                );

                if (attempt < maxAttempts) {
                    sleepBeforeRetry();
                }

            } catch (ResourceAccessException e) {

                lastException = e;

                System.out.println(
                        "XML connection/timeout error on attempt "
                                + attempt
                                + ": "
                                + e.getMessage()
                );

                if (attempt < maxAttempts) {
                    sleepBeforeRetry();
                }
            }
        }

        return new SourceResult<>(
                null,
                false,
                "Benefits Register unavailable after "
                        + maxAttempts
                        + " attempts"
        );
    }
}
