# Gelecek Bilimde Community Science Communication Platform APIs [![Application Status](https://github.com/gelecekbilimde/gelecekbilimde-backend/actions/workflows/application-health-checker.yml/badge.svg)](https://github.com/gelecekbilimde/gelecekbilimde-backend/actions/workflows/application-health-checker.yml)

---

## Postman Collection:

https://www.postman.com/gelecek-bilimde-team/workspace/gelecek-bilimde/collection/37702250-8bfe54f1-1864-410a-b960-ab4e7122dd3e?action=share&creator=37702250

---

## Running All Components as Container on Docker

The following command can be executed to stand up the application and database on Docker.
Then the application can be run and proceed.

```bash
  docker compose up -d --build
```

The following command can be used to remove Docker Containers.

```bash
  docker compose down -v
```

> When used with the -v flag, volumes are also deleted.
> If you want the volumes to be deleted completely, you must also delete the `docker` directory in the application
> directory.
> is required.

---

## Running PostgreSQL Container on Docker

The following command can be executed to stand up the database on Docker.
Then the application can be run and proceed.

```bash
  docker compose up -d --build database
```

The following command can be used to remove Docker Containers.

```bash
  docker compose down -v
```

> When used with the -v flag, volumes are also deleted.
> If you want the volumes to be deleted completely, you must also delete the `docker` directory in the application
> directory.
> is required.

