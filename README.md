# No Wrong Door — Unified Resident API

Brite Spark 2026 — Problem 3: No Wrong Door

A Spring Boot API that combines resident information from two independent legacy
services:

- A paginated REST service providing resident records.
- An XML Benefits Register service that is slow and unreliable.

The unified API continues to provide useful resident data even when the Benefits
Register is unavailable.

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