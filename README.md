# spring-kotlin-coroutines
A simple Spring Boot application built with Kotlin, using Coroutines

## Project Structure

- **Config**: General configuration files
- **Domain**: Entities, DTOs and similar
- **Handler**: Handler functions for the HTTP Routes
- **Repository**: Data repositories
- **Routes**: The API HTTP routes

## How to execute

To start the **Postgres** dev database, simply run `docker-compose up -d` in the root directory.

To start the application, you can run `./gradlew bootRun`. You can also build a **jar** and run it.

When the application starts, it will run the necessary DDL SQL files.