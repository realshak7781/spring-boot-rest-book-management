# Library Management API


A RESTful API for managing books and authors built with Spring Boot, Spring Data JPA, and PostgreSQL.
## 🚀 Getting Started

### Prerequisites

* Java 17+
* Docker & Docker Compose

### Running the App

1. **Start the Database**:
```bash
docker-compose up -d

```


2. **Run the Spring Boot Application**:
```bash
./mvnw spring-boot:run

```



## 🛠️ Tech Stack

* **Framework**: Spring Boot 3.x
* **Data Access**: Spring Data JPA & Hibernate
* **Database**: PostgreSQL
* **JSON Mapping**: Jackson
* **Testing**: JUnit 5 & AssertJ

## 🛣️ API Endpoints

| Method | Endpoint | Description |
| --- | --- | --- |
| `POST` | `/books` | Create a new book |
| `GET` | `/books` | Retrieve all books |
| `GET` | `/authors` | Retrieve all authors |
| `DELETE` | `/authors/{id}` | Delete an author |

---