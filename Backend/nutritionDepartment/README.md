# Nutrition Department Service

Spring Boot microservice for the Campus Hub nutrition department.

## Prerequisites

- [Docker Desktop](https://www.docker.com/products/docker-desktop/)

## Running (Docker Compose — recommended)

Starts both the Spring Boot app and a MySQL 8 database:

```bash
docker compose up --build
```

- App: http://localhost:8080
- MySQL: `localhost:3307` (user: `root`, password: `root`, db: `nutrition_db`)

Database data is persisted in a Docker volume — it survives container restarts.

## Running locally (without Docker)

Requires Java 21, Maven, and a local MySQL instance on port `3307`.

```bash
./mvnw spring-boot:run
```

## Daily development workflow

The recommended approach during active coding — DB runs in Docker, app runs in the IDE:

**1. Start only the database** (once per session):
```bash
docker compose up -d nutrition-db
```

**2. Run the app** from VS Code / IntelliJ — hit the ▶ Run button on `NutritionDepartmentApplication`.

**3. Make a change** → stop (⏹) → run again (▶). Hibernate auto-applies schema changes on startup.

**4. Test endpoints** with Postman at `http://localhost:8080`.

**5. Inspect the DB** in MySQL Workbench: connect to `localhost:3307`, user `root`, password `root`.

> `spring-boot-devtools` is included — it hot-reloads simple changes (service logic etc.) without a full restart. Model/repo changes still require a restart.

## Common commands

```bash
docker compose up --build -d   # build + start in background
docker compose down             # stop & remove containers
docker compose logs -f          # follow live logs
docker compose logs -f nutrition-app  # app logs only
```
