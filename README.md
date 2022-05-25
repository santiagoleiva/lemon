# Lemon 🍋 - L1 Coding Challenge

[Link a consigna](https://www.notion.so/L1-Coding-Challenge-e691fc76ad924dd2b7d1a25ddcf6b69b)

- [Pre-requisitos](#pre-requisitos)
- [Hecho con](#hecho-con)
- [Build](#build)
- [Tests](#tests)
- [Levantando la app](#levantando-la-app)
- [Probando la API](#probando-la-api)

## Pre-requisitos

- Java 11
- Docker Compose

## Hecho con

- Kotlin (1.6.0)
- Spring Boot (2.6.0)
- Gradle (7.2)

## Build

Con una terminal abierta en la raíz del proyecto, ejecutar el comando:

```bash
./gradlew build
```

## Tests

Con una terminal abierta en la raíz del proyecto, ejecutar el comando:

```bash
./gradlew test
```

## Levantando la app

Con una terminal abierta en la raíz del proyecto, ejecutar el comando:

```bash
sudo docker-compose up --build -d
```

## Probando la API

Una vez levantada la aplicación, se podrán consultar los endpoints en `http://localhost:8080`.

> Ver [referencia de API](./docs/REST_DOCS.md)

---

By [Santiago E. Leiva](https://santiagoleiva.com.ar).
