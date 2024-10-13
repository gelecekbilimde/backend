# Gelecek Bilimde Community Science Communication Platform APIs [![Application Status](https://github.com/gelecekbilimde/gelecekbilimde-backend/actions/workflows/application-health-checker.yml/badge.svg)](https://github.com/gelecekbilimde/gelecekbilimde-backend/actions/workflows/application-health-checker.yml)

---

## Project Documents

For other information, you can check [Wiki](https://gelecekbilimde.atlassian.net/wiki/spaces/GBS/overview)
Page.

---

## Postman

### [Documentation](https://documenter.getpostman.com/view/37702250/2sA3sAhTGQ) & [Workspace](https://www.postman.com/gelecek-bilimde-team/workspace/gelecek-bilimde)

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

---

# Project Infrastucture

## 🏛️ Layer Architecture

![](/documents/architecture/gb-architecture.png?raw=true)

## 📦 Package Architecture

![](/documents/architecture/package-architecture-example.png?raw=true)
