# TinyURL Application

## Overview

TinyURL is a URL shortening service built with Java, Spring Boot, PostgreSQL, and Redis. This application allows users to shorten long URLs and retrieve the original URLs using the shortened versions.

## Prerequisites

- Docker & Docker Compose
- Java 17
- Maven

## Getting Started

### Clone the Repository && Compose it up!
```bash
docker-compose build
docker-compose up  

# or 

docker-compose up --build 
```

### Access it!
It should be accessible at [port 8080](http://localhost:8080).

## Usage
### Shorten URL
```bash
curl -X POST http://localhost:8080/api/shorten \
     -H "Content-Type: application/x-www-form-urlencoded" \
     -d "longUrl=https://www.example.com&shortUrl=example"
```
### Get Long URL
```bash
curl http://localhost:8080/url/{shortenedUrl}
```

# LICENSE
Licensed under the [MIT License](https://github.com/melogtm/melurl/blob/main/LICENSE).




