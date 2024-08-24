# Gelecek Bilimde Community Science Communication Platform

[![Build Status](https://github.com/spring-projects/spring-petclinic/actions/workflows/maven-build.yml/badge.svg)](https://github.com/spring-projects/spring-petclinic/actions/workflows/maven-build.yml)

![Elasticsearch](https://img.shields.io/badge/Elasticsearch-=&nbsp;&nbsp;&nbsp;v8.1.0-green) ![Redis](https://img.shields.io/badge/Redis-=&nbsp;&nbsp;v6.2.7-green) ![Postgres](https://img.shields.io/badge/Postgres-=&nbsp;&nbsp;v15.3-green) ![Docker](https://img.shields.io/badge/Docker-=&nbsp;&nbsp;v20.10.22-green) ![Docker Compose](https://img.shields.io/badge/Docker&nbsp;Compose-=&nbsp;&nbsp;v2.15.1-green)

---

## Postman Collection: Gelecek Bilimde API

https://www.postman.com/gelecek-bilimde-team/workspace/gelecek-bilimde/collection/37702250-8bfe54f1-1864-410a-b960-ab4e7122dd3e?action=share&creator=37702250

## Related Projects

[Frontend](https://github.com/gelecekbilimde/gelecek-bilimde-frontend)

[Android](https://github.com/gelecekbilimde/Android-Application)

---

## Local Environment Setup

The following command can be executed to stand up the application's dependencies on Docker.
Then the application can be run and proceed.

```bash
  docker compose up -d --build
```

The following command can be used to remove Docker Containers.

```bash
  docker compose down -v
```

> When used with the -v flag, volumes are also deleted.
 If you want the volumes to be deleted completely, you must also delete the `docker` directory in the application directory.
 is required.


---


