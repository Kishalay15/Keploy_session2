# Task Manager API (Spring Boot)

## 🔗 API Endpoints

- `GET /api/tasks`
- `POST /api/tasks`
- `PUT /api/tasks/{id}`
- `DELETE /api/tasks/{id}`

## 🛠 Setup Instructions

1. Clone the repo
2. Setup MySQL DB
3. Add credentials in `application.properties`
4. Run using `./mvnw spring-boot:run`

## 🧪 Sample Request

```bash
curl -X GET http://localhost:8080/api/tasks
