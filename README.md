# No Wrong Door — Unified Resident API

Brite Spark 2026 — Problem 3: No Wrong Door

A Spring Boot API that provides a unified resident view by combining data from two independent legacy services:

- A paginated REST service providing resident records.
- An XML Benefits Register service that is slow and unreliable.

The unified API continues to provide useful resident data even when the Benefits Register is unavailable.

## Architecture

```text
                         Client
                           |
                           v
                  Unified REST API
                    Spring Boot
                           |
                    UnifiedService
                    /            \
                   /              \
                  v                v
          ResidentClient     BenefitsClient
                |                  |
                v                  v
          REST Service         XML Service
             :8081                :8082
```

Each external source has its own client so that failures or behavior changes in one source do not require changes to the other source or the unified assembly logic.

## Features

- Paginated REST data retrieval.
- Handles duplicate records appearing across REST pages.
- Combines data from REST and XML sources.
- XML-to-Java DTO mapping.
- Graceful degradation when the Benefits Register is unavailable.
- 3-second read timeout for the XML service.
- Up to 3 attempts for XML failures.
- Clear source availability and error information.
- Handles the Day 2 Benefits Register failure rate of approximately 40%.

## How to Run

### Prerequisites

- Java
- Maven
- Python 3

### 1. Start the REST mock service

From the folder containing the provided REST mock service:

```powershell
python rest_service.py --port 8081
```

The REST service runs on:

```text
http://127.0.0.1:8081
```

You can verify it with:

```text
http://127.0.0.1:8081/residents?page=1
```

### 2. Start the XML Benefits Register

From the folder containing the provided XML service:

```powershell
python3 services/xml_service.py --port 8082 --failure-rate 0.40
```

The XML service runs on:

```text
http://127.0.0.1:8082
```

The `0.40` failure rate is intentional. It simulates the Day 2 requirement where the Benefits Register fails on approximately 40% of calls.

### 3. Start the Spring Boot application

Open another terminal in the project root and run:

```powershell
mvn spring-boot:run
```

The Spring Boot application runs on:

```text
http://localhost:8085
```

### 4. Call the unified API

Use:

```text
GET http://localhost:8085/api/residents/unified
```

The API combines resident information from the REST service and the Benefits Register.

## Graceful Degradation

If the REST source is available but the Benefits Register fails, the unified API does not return a bare server error.

Instead, the resident data remains available and the Benefits source is explicitly marked as unavailable.

Example:

```xml
<benefits>
    <available>false</available>
    <error>Benefits Register unavailable after 3 attempts</error>
</benefits>

<residentSourceAvailable>true</residentSourceAvailable>
```

This allows the caller to distinguish between:

- Benefits data being available.
- Benefits data being unavailable.
- Resident data being available.

## Retry and Timeout Policy

The Benefits Register is slow and unreliable.

The application:

1. Attempts the XML request.
2. Retries failed XML requests up to 3 attempts.
3. Uses a 3-second read timeout.
4. If all attempts fail, marks the Benefits source as unavailable.
5. Returns the data available from the other source.

The application does not retry indefinitely because repeated retries would increase response time and place additional load on an unreliable dependency.

## Deduplication

The REST service is paginated and can return the same resident on more than one page.

The application tracks resident IDs while processing the pages and only adds a resident when its ID has not already been seen.

Verified result:

```text
Total fetched: 661
Duplicates removed: 41
Unique residents: 620
```

## Verified Behavior

The following scenarios were tested:

### 1. Normal XML success

```text
XML request succeeded on attempt 1
```

Benefits data is returned normally.

### 2. XML 500 followed by retry

```text
Attempt 1 → 500
Attempt 2 → success
```

The Benefits data is successfully returned.

### 3. XML fails on all attempts

```text
Attempt 1 → 500
Attempt 2 → 500
Attempt 3 → 500
```

The Benefits source is marked unavailable, while resident data from the REST source is still returned.

### 4. XML timeout

The XML service was temporarily configured to respond slower than the 3-second timeout.

```text
Attempt 1 → Read timed out
Attempt 2 → Read timed out
Attempt 3 → Read timed out
```

The unified API still responds and gracefully reports the Benefits source as unavailable.

### 5. REST pagination and deduplication

```text
Total fetched: 661
Duplicates removed: 41
Unique residents: 620
Total pages fetched: 27
```

## Day 2 Change

On Day 2, the Benefits Register was changed to fail permanently on approximately 40% of calls.

The architecture did not require a redesign because the REST and XML integrations were already separated into independent clients.

The existing retry, timeout, and graceful-degradation behavior was tested against the new failure rate.

## Project Structure

```text
no-wrong-door-rest-starter/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/britespark/nowrongdoor/
│       │       ├── client/
│       │       ├── controller/
│       │       ├── dto/
│       │       └── service/
│       │
│       └── resources/
│
├── .gitignore
├── AI-USAGE.md
├── DECISIONS.md
├── README.md
└── pom.xml
```

## Design Decisions

See [DECISIONS.md](DECISIONS.md) for the design decisions made during development, including:

- Independent source clients.
- Graceful degradation.
- `SourceResult`.
- Timeout policy.
- Retry policy.
- Day 2 failure-rate change.
- Decisions made under the hackathon time constraint.

## AI Usage

See [AI-USAGE.md](AI-USAGE.md) for information about how AI tools were used during development.

## Demonstration

To demonstrate the solution:

1. Start the REST mock service.
2. Start the XML Benefits Register with a 40% failure rate.
3. Start the Spring Boot application.
4. Call `/api/residents/unified` repeatedly.
5. Observe successful XML requests.
6. Observe XML 500 responses followed by retries.
7. Observe graceful degradation when all retries fail.
8. Verify that resident data remains available when the Benefits Register is unavailable.