# AI Usage

AI tools were used as a development aid during the implementation of this project.

## How AI was used

AI assistance was used for:

- Understanding the problem requirements and breaking them into implementation steps.
- Explaining REST and XML service integration concepts.
- Discussing Spring Boot `RestClient` usage.
- Understanding XML-to-Java DTO mapping and wrapper classes.
- Reviewing retry, timeout, and graceful degradation approaches.
- Debugging implementation errors and exceptions.
- Reviewing Git and project workflow steps.
- Improving documentation and explaining design decisions.

## Human responsibility

The implementation was developed, tested, and verified by the project author.

AI suggestions were reviewed before being applied, and the resulting code was tested against the provided REST and XML services.

## Day 2 change

After the Benefits Register was changed to fail approximately 40% of calls, AI assistance was used to reason about the existing resilience design and verify that the source adapter, retry policy, timeout, and graceful degradation behavior were appropriate.

The final implementation and testing decisions were made based on the actual behavior of the application and the hackathon requirements.