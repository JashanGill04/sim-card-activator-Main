# SIM Card Activator

This project is a Spring Boot microservice that activates SIM cards by calling an external actuator service.

## Tech Stack
- Java 11
- Spring Boot
- Maven
- REST API

## How It Works
1. Exposes POST `/api/activate-sim`
2. Accepts ICCID and customer email
3. Calls actuator service running on port 8444
4. Returns activation result

## API Endpoint

### POST /api/activate-sim
Request:
```json
{
  "iccid": "123456789",
  "customerEmail": "test@example.com"
}

# sim-card-activator-Main
