# Nutrition Department Service

Spring Boot microservice for the Campus Hub nutrition department.

## Prerequisites

- Java 21
- Maven
- Docker Desktop

## Database Setup

Run the following command to start the MySQL database in Docker:

```bash
docker run --name nutrition-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=nutrition_db -p 3307:3306 -d mysql:8.0
```

This creates a MySQL 8.0 container with:
- **Port:** `3307` (host) → `3306` (container)
- **Database:** `nutrition_db`
- **Username:** `root`
- **Password:** `root`

> MySQL takes a few seconds to initialize on first boot. If the app fails to connect immediately, wait ~10 seconds and retry.

## Running the App

```bash
mvn spring-boot:run
```
