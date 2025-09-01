# Java Spring Boot JDBC Example

A reference implementation for building RESTful APIs using Spring Boot and JDBC.

## Features

- CRUD operations for sample entities
- JDBC-based persistence (no ORM)
- Layered architecture: Controller, Service, Repository
- Exception handling and validation
- Supports easy database switching (H2, MySQL, PostgreSQL)

## Getting Started

### Prerequisites

- Java 8 or newer
- Maven or Gradle
- Any JDBC-compatible relational database (H2 is used by default)

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/nilesh-som80/java-spring-boot-jdbc.git
   cd java-spring-boot-jdbc
   ```

2. **Configure Database**
   - Edit `src/main/resources/application.properties` to set up your database connection.

   Example for H2 (default):
   ```
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=
   ```

   Example for MySQL:
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/yourdb
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```
   The application will start at [http://localhost:8080](http://localhost:8080).

## API Endpoints

| Method | Endpoint             | Description         |
|--------|----------------------|---------------------|
| GET    | `/api/user`          | List all entities   |
| GET    | `/api/user/{id}`     | Get entity by ID    |
| POST   | `/api/user`          | Create new entity   |
| PUT    | `/api/user/{id}`     | Update entity       |
| DELETE | `/api/user/{id}`     | Delete entity       |

_Update endpoint paths to match your code as needed._

## Project Structure

```
src/
  main/
    java/
      com/
        example/
          controller/    # REST endpoints
          service/       # Business logic
          repository/    # JDBC operations
          model/         # Data entities
    resources/
      application.properties
```

## Technologies Used

- Spring Boot
- JDBC
- Maven
- H2/MySQL (pluggable)

## Contributing

Contributions welcome! Please open issues or submit pull requests.

## License

This project is licensed under the MIT License.

---

**Author:** [nilesh-som80](https://github.com/nilesh-som80)
