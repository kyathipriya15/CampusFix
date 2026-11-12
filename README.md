# CampusFix - SLA-Aware Campus Maintenance Portal

**An original educational full-stack Java project scaffold.** CampusFix lets students report campus maintenance issues and lets a facilities operator review, prioritize, and resolve them. It includes a responsive Thymeleaf UI, Java/Spring Boot backend, persistence through Spring Data JPA, REST APIs, and test cases for the rules engine.

> **Project Status:** Functional Java Full-Stack MVP, successfully built and tested locally using Java 21 and Maven. All 4 JUnit tests passed. Ticket creation, priority assignment, status updates, and dashboard functionality were verified. Source code is publicly available on GitHub. The application is not publicly deployed and does not yet include authentication.

## Why this is more than a basic CRUD project

1. **SLA classification** based on issue category and safety keywords: critical 4 h, high 24 h, medium 48 h, low 72 h.
2. **Duplicate guard** prevents a second active ticket for the same category, building and room within a 48-hour window.
3. **Workflow rules** only allow tickets to advance from `OPEN` to `IN_PROGRESS` or `RESOLVED`; resolved tickets are read-only.
4. **Operator dashboard** supports search, status filtering and overdue counts.
5. **JSON REST API** in addition to server-rendered UI.

## Stack
Java 21 · Spring Boot 3.4.2 · Spring MVC · Spring Data JPA / Hibernate · Thymeleaf · HTML/CSS · H2 (default demo database) · MySQL (optional) · JUnit 5 · Maven.

**Architecture:** Browser → Thymeleaf Controller / REST Controller → TicketService → TicketRepository (JPA) → H2 / MySQL.

## Run locally
Prerequisites: **JDK 21** and **Maven 3.9+**, internet access for Maven dependencies on the first run.

```bash
mvn clean test
mvn spring-boot:run
```

Open **http://localhost:8080**. The default H2 database persists in the local `data/` directory (ignored by Git). For a clean start, shut down the app and delete the `data/` folder.

### Optional MySQL
Create a local MySQL 8 database or let the JDBC URL create it (your account must have CREATE DATABASE permission). Then use environment variables:

```bash
export DB_USERNAME=root
export DB_PASSWORD=YOUR_LOCAL_PASSWORD
export DB_URL='jdbc:mysql://localhost:3306/campusfix?createDatabaseIfNotExist=true'
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```

Never commit actual database passwords to GitHub. `application-mysql.properties` uses environment variable placeholders.

## Demo walkthrough
1. Create ticket: `Projector flashes sparks`, category `ELECTRICAL`, building `Block A`, room `A-101`.
2. Verify `CRITICAL` priority and due time 4 hours after creation.
3. Try the same category/room within 48 h → request blocked as duplicate.
4. Create a separate `CLEANING` ticket → `LOW`, 72-hour SLA.
5. Open ticket details and advance status to `IN_PROGRESS`, then `RESOLVED`.
6. Filter the dashboard by status; inspect JSON via `GET /api/tickets`.

## REST API
- `GET /api/tickets` - list tickets
- `GET /api/tickets/{id}` - details
- `POST /api/tickets` - create ticket
- `PATCH /api/tickets/{id}/status` - transition status

Example:

```bash
curl -X POST http://localhost:8080/api/tickets   -H 'Content-Type: application/json'   -d '{"title":"Projector not working","description":"No display output","category":"CLASSROOM","building":"B Block","room":"B-203"}'
```

```bash
curl -X PATCH http://localhost:8080/api/tickets/1/status   -H 'Content-Type: application/json' -d '{"status":"IN_PROGRESS"}'
```

## Validation / honest limits
- Input field lengths and required fields are validated in the form and in the REST request DTO.
- Duplicate detection is a simple demo rule, **not concurrency-safe** without a stronger database constraint/transaction strategy.
- SLA is a demo rule, not a college policy. It calculates due time; no real-time notifications or scheduler yet.
- There are **no student/admin accounts, authorization, file uploads or security protections**. Do not deploy the demo publicly as-is.
- **Testing:** Successfully executed `mvn clean test` with 4 JUnit tests passing (0 failures, 0 errors).
- **Manual Verification:** Tested ticket creation, automatic priority assignment, status updates, and dashboard filtering.
f
## Recommended contributions by the student (before claiming ownership)
1. Set up the project locally and record a working demo (2 min).
2. Add login, role-based access and authorization (Spring Security) or clearly describe why MVP is local only.
3. Add one distinct feature personally: e.g., ticket assignment to a maintenance team, room-level heatmap or ticket history.
4. Add at least 3 integration tests, including duplicate prevention and workflow validation.
5. Write screenshots and document precisely what you implemented yourself.

## Repository publishing
## GitHub Repository

**Source Code:** https://github.com/kyathipriya15/CampusFix

**Branch:** main

**Status:** Public repository with working source code, setup instructions, and unit tests.

## Attribution
This scaffold was generated with AI assistance. The student should disclose tool use where required by their college/interviewer, understand and improve all code, and accurately describe their own contribution. No third-party repo was copied into this package.
