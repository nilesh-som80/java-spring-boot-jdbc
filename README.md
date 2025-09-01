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
}
```

---

**Note:** All responses contain a status and message for easier error handling and client integration.