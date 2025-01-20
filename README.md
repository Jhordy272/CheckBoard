# Check Board API

A managerial system API designed to create and manage check boards for monitoring budgets, assigning personnel by week, and defining the roles of assigned staff. It also includes functionalities for managing users, roles, service lines, job titles, divisions, and projects. Built with Spring Boot, the API adheres to RESTful principles for seamless integration with other systems and ensures secure access and operations via JWT-based authentication.

## Table of Contents

- [Features](#features)
- [API Documentation](#api-documentation)
- [Requirements](#requirements)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Request Examples](#request-examples)
- [Contributing](#contributing)
- [License](#license)

## Features

- Management of users and roles.
- Project management with user associations.
- Project-Based Hour Planning.
- Authentication and authorization using **JWT**.
- JSON format responses.

## API Documentation

This project includes **Swagger** for API documentation, which allows you to view and test all available endpoints directly from your browser.

### Accessing Swagger UI

Once the application is running, you can access the Swagger UI at:

- **URL**: `http://localhost:8080/swagger-ui.html`

Through this interface, you can:
- View all endpoints, along with their request and response formats.
- Execute requests for each endpoint by providing necessary parameters and headers.
- Test endpoint responses in real time.

### Setup for Swagger (if needed)

Swagger is already configured within the project. If you need to adjust any settings, you can do so in `application.properties`:

```properties
springdoc.swagger-ui.path=/swagger-ui.html
```

## Requirements

- **Language**: Java 21+
- **Framework**: Spring Boot 2.5+
- **Database**: MySQL, PostgreSQL, or another Spring Data JPA-compatible database
- **Tools**: Maven

## Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/Jhordy272/CheckBoard
    ```

2. Navigate to the project directory:

    ```bash
    cd check_board
    ```

3. Install the required dependencies:

    ```bash
    mvn install
    ```


4. Configure the environment variables as indicated in the [Configuration](#configuration) section.

## Configuration

Set the required environment variables in a `.env` file or in `application.properties`:

```properties
# Database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/test_case_reporting
spring.datasource.username=username
spring.datasource.password=password

# JWT configuration
jwt.secret=secret_key

# Server configuration
server.port=8080 
```

## Usage
To start the server, run:
```bash 
mvn spring-boot:run
```
The API will be available at http://localhost:8080.


## Endpoints

### Authentication
- `POST /auth/login`: User authentication and JWT token generation.
- `POST /auth/register`: User registration with role QA and JWT token generation.

### Role Management
- `GET /roles`: Retrieves all roles.
- `GET /roles/{id}`: Retrieves a role by ID.
- `POST /roles`: Creates a role.
- `PUT /roles/{id}`: Modifies a role by ID.
- `DELETE /roles/{id}`: Deletes a role by ID.

### User Management
- `GET /users`: Retrieves all roles.
- `GET /users/{id}`: Retrieves a role by ID.
- `POST /users`: Creates a role.
- `PUT /users/{id}`: Modifies a role by ID.
- `DELETE /users/{id}`: Deletes a role by ID.

### Service Line Management
- `GET /service-line`: Retrieves all services lines.
- `GET /service-line/{id}`: Retrieves a service line by ID.
- `POST /service-line`: Creates a service line.
- `PUT /service-line/{id}`: Modifies a service line by ID.
- `DELETE /service-line/{id}`: Deletes a service line by ID.

### Job Title Management
- `GET /job-title`: Retrieves all job titles.
- `GET /job-title/{id}`: Retrieves a job title by ID.
- `POST /job-title`: Creates a job title.
- `PUT /job-title/{id}`: Modifies a job title by ID.
- `DELETE /job-title/{id}`: Deletes a job title by ID.

### Division Management
- `GET /division`: Retrieves all divisions titles.
- `GET /division/{id}`: Retrieves a job title by ID.
- `POST /division`: Creates a job title.
- `PUT /division/{id}`: Modifies a job title by ID.
- `DELETE /division/{id}`: Deletes a job title by ID.

### Project Management
- `GET /projects`: Retrieves all projects.
- `GET /projects/{id}`: Retrieves a project by ID.
- `POST /projects`: Creates a projects.
- `PUT /projects/{id}`: Modifies a project by ID.
- `DELETE /projects/{id}`: Deletes a project by ID.

## Request Examples

### Auth a User

`POST /auth/login`

**Headers:**
- `Content-Type: application/json`
- `Authorization: Bearer <JWT_TOKEN>`

**Body:**
```json
{
  "username": "user1",
  "password": "123456"
}
```

### Create a Role

`POST /roles`

**Headers:**
- `Content-Type: application/json`
- `Authorization: Bearer <JWT_TOKEN>`

**Body:**
```json
{
  "name": "role 1"
}
```

### Create a User

`POST /users`

**Headers:**
- `Content-Type: application/json`
- `Authorization: Bearer <JWT_TOKEN>`

**Body:**
```json
{
  "username": "user1",
  "password": "123456",
  "email": "user1@mail.com",
  "idRol": 1,
  "jobTitle": 1
}
```
### Create a Job Title

`POST /job-title`

**Headers:**
- `Content-Type: application/json`
- `Authorization: Bearer <JWT_TOKEN>`

**Body:**
```json
{
  "name": "jobtitle1",
  "hourlyRate": 100
}
```

### Create a Project

`POST /projects`

**Headers:**
- `Content-Type: application/json`
- `Authorization: Bearer <JWT_TOKEN>`

**Body:**
```json
{
  "name": "project1",
  "year": "2023",
  "startDate": "2024-10-31T02:16:56.445Z",
  "endDate": "2024-10-31T02:16:56.445Z",
  "fees": 1000,
  "extraExpenses": 100,
  "budgetedHours": 20,
  "hourlyRate": 20,
  "total": 1100,
  "division": 1,
  "serviceLine": 1,
  "manager": 1,
  "observations": "observation project1"
}
```

## Contributing

Contributions are welcome! If you'd like to improve the project, please follow these steps:

1. **Fork** the repository.
2. **Create a branch** for your feature or fix (`git checkout -b feature/YourFeature`).
3. **Commit** your changes (`git commit -m 'Add new feature'`).
4. **Push** to the branch (`git push origin feature/YourFeature`).
5. Open a **Pull Request** and describe your changes.

Please ensure your code follows the project's coding standards and includes relevant tests. We appreciate any enhancements, bug fixes, or documentation improvements!

## License

This project is licensed under the MIT License. You are free to use, modify, and distribute this software. See the [LICENSE](license) file for more details.# check_board
# check_board
