# Java Spring Boot JDBC Example

A reference implementation for building RESTful and GraphQL APIs using Spring Boot and JDBC.

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

## GraphQL API Documentation

This project implements a GraphQL API for user management. Below are the available queries and mutations, along with their types and expected responses.

### Queries

#### getUser(id: ID!): ResponseSingleUser
- Fetch a single user by ID.
- Returns:  
  ```graphql
  type ResponseSingleUser {
    status: String!
    message: String!
    data: User
  }
  ```

#### getAllUsers: ResponseUserList
- Fetch all users.
- Returns:  
  ```graphql
  type ResponseUserList {
    status: String!
    message: String!
    data: [User!]!
  }
  ```

---

### Mutations

#### createUser(input: UserInput!): ResponseSingleUser
- Create a new user.
- Returns the created user and a status message.

#### updateUser(id: ID!, input: UserInput!): ResponseSingleUser
- Update an existing user’s details.
- Returns the updated user and a status message.

#### deleteUser(id: ID!): ResponseDelete
- Delete a user by ID.
- Returns a status message and a boolean indicating success.

---

### Types

#### User
```graphql
type User {
  id: ID!
  name: String!
  email: String!
  role: UserRole!
  status: UserStatus!
}
```

#### UserInput
```graphql
input UserInput {
  name: String!
  email: String!
  role: UserRole!
  status: UserStatus!
}
```

#### Enums
```graphql
enum UserRole {
  ADMIN
  EDITOR
  VIEWER
}

enum UserStatus {
  ACTIVE
  INACTIVE
}
```

---

### Example Query

```graphql
query {
  getUser(id: "1") {
    status
    message
    data {
      id
      name
      email
      role
      status
    }
  }
}
```

### Example Mutation

```graphql
mutation {
  createUser(input: {
    name: "John Doe",
    email: "john@example.com",
    role: ADMIN,
    status: ACTIVE
  }) {
    status
    message
    data {
      id
      name
    }
  }
}
```

---

**Note:** All responses contain a status and message for easier error handling and client integration.

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