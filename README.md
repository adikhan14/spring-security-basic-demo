# Spring Security Basic Demo

A Spring Boot application demonstrating HTTP Basic Authentication using Spring Security with in-memory user storage.

## Tech Stack

- Java 17
- Spring Boot 3.5.14
- Spring Security
- Maven

## Project Structure

```
src/main/java/spring/security/basic/demo/
├── config/
│   └── SecurityConfig.java          # Security configuration, beans
├── controller/
│   └── UserController.java          # REST endpoints
├── dto/
│   └── AuthRequest.java             # Request body DTO
├── service/
│   ├── CustomUserDetailsService.java # Loads user from in-memory store
│   └── UserService.java             # In-memory user storage (ConcurrentHashMap)
└── SpringSecurityBasicDemoApplication.java
```

## How It Works

- Users are stored in a `ConcurrentHashMap` (in-memory, cleared on restart)
- Passwords are hashed using **BCrypt** before storage
- Authentication is handled via **HTTP Basic** on every request
- Spring Security's `DaoAuthenticationProvider` wires `CustomUserDetailsService` and `BCryptPasswordEncoder` together

## API Endpoints

### Register
```
POST /register
Content-Type: application/json

{
  "username": "adil",
  "password": "adil123"
}
```
Response: `201 Created`

---

### Login
```
POST /login
Content-Type: application/json

{
  "username": "adil",
  "password": "adil123"
}
```
Response: `200 OK`

---

### Hello (protected)
```
GET /hello
Authorization: Basic YWRpbDphZGlsMTIz
```
> Base64 of `adil:adil123`

Response: `200 OK` — `Hello`

## Postman Collection

Import `spring-security-basic-demo.postman_collection.json` into Postman to get all three requests pre-configured with sample data.

> The collection uses a `{{baseUrl}}` variable set to `http://localhost:8080`. Change it if your server runs on a different port.

## Running the App

```bash
./mvnw spring-boot:run
```

Server starts on `http://localhost:8080`

## Testing with curl

```bash
# Register
curl -X POST http://localhost:8080/register \
  -H "Content-Type: application/json" \
  -d '{"username":"adil","password":"adil123"}'

# Login
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"adil","password":"adil123"}'

# Access protected endpoint
curl -X GET http://localhost:8080/hello \
  -u adil:adil123
```
