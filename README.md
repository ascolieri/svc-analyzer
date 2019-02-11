# svc-analyzer
[![Build Status](https://travis-ci.org/ascolieri/svc-analyzer.svg?branch=master)](https://travis-ci.org/ascolieri/svc-analyzer)
[![codecov](https://codecov.io/gh/ascolieri/svc-analyzer/branch/master/graph/badge.svg)](https://codecov.io/gh/ascolieri/svc-analyzer)

Api that search if a human being is mutant or not based on DNA sequence

## Technology stack
* Spring Boot 2.1
* Swagger for documentation

## Deployed app and swagger spec
<https://svc-analyzer.herokuapp.com/swagger-ui.html>

## How to run
* Set up a local database by running<br/>
    `docker run -d  -p 5432:5432 -e POSTGRES_PASSWORD=admin -e POSTGRES_USER=admin -e POSTGRES_DB=dna --name analizer-pg postgres`

* Execute the service<br/>
    `mvn spring-boot:run`

* Go to<br/>
    `http://localhost:8080/swagger-ui.html`

