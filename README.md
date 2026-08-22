# No Wrong Door — REST milestone

This is the first small step for Brite Spark 2026 Problem 3. It calls only the provided REST mock service. It does **not** call the XML service yet.

## What happens

1. You call `GET http://localhost:8080/api/residents/page-1`.
2. `ResidentController` asks `ResidentClient` for data.
3. `ResidentClient` makes `GET http://127.0.0.1:8081/residents?page=1`.
4. Spring/Jackson maps the returned JSON to a `JsonNode` and returns it to your browser or API client.
5. The same JSON is printed neatly in the Spring Boot terminal.

## Files to understand

| File | Why it exists |
| --- | --- |
| `pom.xml` | Defines Spring Boot and the `spring-boot-starter-web` dependency. This one dependency brings in the web server, `RestClient`, and Jackson JSON support. |
| `NoWrongDoorApplication.java` | Starts Spring Boot. |
| `ResidentClient.java` | The code that calls the external REST service. `JsonNode` is a flexible Java representation of JSON; it is a good first step before making Java classes for every resident field. |
| `ResidentController.java` | Exposes our own endpoint at `/api/residents/page-1`. |
| `application.properties` | Makes our application use port 8080, separate from the mock REST service on 8081. |

## Run it

### 1. Start the provided mock REST service

From the folder containing the competition files:

```powershell
python rest_service.py --port 8081
```

Check it directly in a browser or with curl:

```text
http://127.0.0.1:8081/residents?page=1
```

### 2. Start this Spring Boot project

Open a second terminal in this project folder and run:

```powershell
mvn spring-boot:run
```

This requires Maven and an internet connection the first time, so Maven can download Spring Boot libraries. You can also open the folder in IntelliJ IDEA and run `NoWrongDoorApplication`.

### 3. Call our endpoint

Open:

```text
http://localhost:8080/api/residents/page-1
```

You should see the same JSON supplied by the REST mock service. Look in the Spring Boot terminal too: it prints the `JsonNode` response.

## Why `JsonNode` first?

We do not yet know every field and nesting level in the supplied resident JSON. Mapping into `JsonNode` proves that the HTTP call and JSON conversion work without guessing at fields. Once that is working, the next REST-only step is to inspect one response and create small Java `record` classes for the fields we need.
