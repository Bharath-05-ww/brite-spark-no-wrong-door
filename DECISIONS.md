# Design Decisions

## 1. Independent source clients

REST and XML integrations are implemented as separate clients.

- `ResidentClient` communicates with the REST source.
- `BenefitsClient` communicates with the XML source.
- `UnifiedService` is responsible for combining their results.

This keeps source-specific behavior isolated from the unified response logic.

## 2. Graceful degradation

The unified API does not fail completely when the Benefits Register is unavailable.

If the REST source is available but the Benefits Register fails, the API still returns the resident data and explicitly reports that the Benefits Register is unavailable.

This prevents one dependency failure from causing the entire API to fail.

## 3. SourceResult

A `SourceResult<T>` wrapper is used to represent both successful and failed source calls.

It contains:

- the returned data
- source availability
- an error message when the source is unavailable

This avoids using `null` alone to represent different failure conditions.

## 4. Timeout

A 3-second read timeout is configured for the Benefits Register.

The XML service can be slow, so the timeout prevents the unified API from waiting indefinitely for an unavailable or excessively slow dependency.

## 5. Retry

The Benefits Register is retried up to three times for transient server and connection/timeout failures.

A short delay is used between attempts.

If all attempts fail, the source is marked unavailable and the unified API continues with the data that is available from other sources.

## 6. Day 2 change: 40% Benefits Register failure rate

On Day 2, the Benefits Register was changed to fail on approximately 40% of calls permanently.

We did not change the overall architecture because the source integration was already isolated behind `BenefitsClient` and `SourceResult`.

We retained controlled retries and timeout handling and verified that the unified API continues to work when the Benefits Register fails.

## 7. What we chose not to change

We did not increase the retry count indefinitely to try to make the Benefits Register appear reliable.

Repeated retries would increase response time and place additional load on an already unreliable dependency.

Instead, after the configured attempts fail, the API degrades gracefully and reports the source as unavailable.

## 8. What we would have done differently

If we had known from the beginning that the Benefits Register would permanently fail approximately 40% of the time, we would have considered designing the resilience policy around this failure rate from the start and planned the retry and timeout behavior as explicit source-level policies.

However, the existing adapter-based design allowed us to handle the change without rewriting the unified service.