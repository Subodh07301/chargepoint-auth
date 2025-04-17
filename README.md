# ⚡ ChargePoint Authorization Service

A simulation of an **Electric Vehicle Charging Station Management System (CSMS)** authorization flow using **Java**, **Spring Boot**, and **Kafka**. This service authenticates driver identifiers (like RFID or virtual tokens) and returns authorization statuses: `Accepted`, `Rejected`, `Unknown`, or `Invalid`.

---

## Features

- REST API for authorization requests
- In-memory whitelist with permission flags
- Kafka-based asynchronous messaging
- Validation using Jakarta Bean Validation
- Global exception handling
- Centralized structured logging
- Unit & Integration Tests

---

## Use Case

When a charging station sends a driver identifier to authorize a session, the backend:
- Checks if the identifier exists in the whitelist
- Decides whether to allow charging
- Responds with the appropriate `authorizationStatus`

---

## Tech Stack

| Layer         | Technology                |
|---------------|---------------------------|
| Language      | Java 17                   |
| Framework     | Spring Boot 3.x           |
| Messaging     | Apache Kafka              |
| Validation    | Jakarta Bean Validation   |
| Testing       | JUnit 5, Mockito, MockMvc |
| Build Tool    | Gradle                    |
| Logging       | SLF4J + Logback           |

---

## Project Structure

```
chargepoint-auth/
├── api/            # REST controllers
├── model/          # DTOs and response models
├── service/        # Business logic
├── kafka/          # Kafka producer
├── config/         # Kafka config
├── whitelist/      # In-memory whitelist
├── exception/      # Global error handling
├── test/           # Unit and integration tests
└── resources/
    └── application.yml
```

---

## Getting Started

### Prerequisites
- Java 17+
- Kafka and Zookeeper running (e.g., via Docker)
- Gradle installed (or use wrapper)

---

### Setup & Run

1. **Clone the project**
```bash
git clone https://github.com/yourname/chargepoint-auth.git
cd chargepoint-auth
```

2. **Start Kafka**
```bash
docker-compose up -d  # assuming you have docker-compose for Kafka
```

3. **Build & Run the Application**
```bash
./gradlew build
./gradlew bootRun
```

4. **Run Tests**
```bash
./gradlew test
```

---

## API Usage

### Endpoint
```http
POST /api/authorize
```

### Request Body
```json
{
  "stationUuid": "25aac66b-6051-478a-95e2-6d3aa343b025",
  "driverIdentifier": {
    "id": "id12345678901234567890"
  }
}
```

> The `id` field must be 20–80 characters long.

### Response
```json
{
  "authorizationStatus": "Accepted"  // or Rejected, Unknown, Invalid
}
```

---

## Test Coverage

| Test Type         | Tool            | Description                          |
|------------------|------------------|--------------------------------------|
| Unit Test         | JUnit + Mockito | Service logic unit tests             |
| Integration Test  | Spring Boot Test + MockMvc | REST endpoint validation     |
| Kafka Messaging   | Mocked in tests | Kafka producer verified via mock     |

---

## Security & Future Enhancements

- Add JWT-based authentication for production
- Replace in-memory whitelist with a DB (e.g., PostgreSQL)
- Add monitoring (Prometheus/Grafana)
- Integrate OpenAPI (Swagger) for API docs
- Add Kafka consumer to fully decouple frontend/backend flow

---

## Contributing

Feel free to fork and PR improvements! This project simulates a real-world architecture and is perfect for experimentation or interview preparation.

---

## Author

**Subodh Kumar**  
Gaya, Bihar | 📞 9148628797  
[subodh.shiva@gmail.com]  
GitHub: [github.com/yourname](https://github.com/subodh07301)

---

## License

MIT License. See `LICENSE` file for details.
