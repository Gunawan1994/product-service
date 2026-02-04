## Tech Stack
- Java 17, Spring Boot
- PostgreSQL
- Maven
- Swagger (springdoc-openapi)

## Configuration
- Base config: see [src/main/resources/application.yaml](src/main/resources/application.yaml)
- Environment overrides imported from [env.yaml](env.yaml) (optional). Default values:
  - `server.port`: 8000
  - `server.servlet.context-path`: /v1/api
  - PostgreSQL URL: `jdbc:postgresql://localhost:5432/postgres`
  - Username: `postgres`, Password: `12345`

When running with Docker Compose, the app uses the `db` container hostname automatically.

## Build & Run (Maven)
```
.\mvnw clean package
.\mvnw spring-boot:run
```
App runs at: http://localhost:8000/v1/api

## Build & Run (Docker Compose)
Prerequisites: Docker Desktop

```
docker compose up --build -d
```
- API base: http://localhost:8000/v1/api
- Swagger UI: http://localhost:8000/swagger-ui.html

Compose details: see [docker-compose.yml](docker-compose.yml)
- `db`: PostgreSQL 16 with persistent volume (`db-data`) and healthcheck
- `app`: built from [Dockerfile](Dockerfile), exposes `8000` and connects to `db`

## API Endpoints
Base path: `/v1/api`

Products: see [src/main/java/com/example/product/controller/ProductController.java](src/main/java/com/example/product/controller/ProductController.java)
- `GET /product` — list products
- `GET /product/{id}` — get product by id
- `POST /product` — create product
- `PUT /product/{id}` — update product by id
- `DELETE /product/{id}` — delete product by id

Categories: see [src/main/java/com/example/product/controller/CategoryController.java](src/main/java/com/example/product/controller/CategoryController.java)
- `GET /category` — list categories
- `POST /category` — create category

## Development Tips
- Update DB credentials in [env.yaml](env.yaml) or via environment variables.
- For Compose, tweak `POSTGRES_*` and `SPRING_DATASOURCE_*` in [docker-compose.yml](docker-compose.yml)