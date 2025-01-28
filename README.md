# Loan API Documentation

## Overview

The Loan API is a RESTful service designed to handle loan-related operations, such as applying for loans, retrieving loan details, and managing loan statuses. This API is built with Spring Boot and provides a robust, scalable, and secure way to manage loan applications.

### Key Features:

- Apply for a loan
- Retrieve loan details by ID
- List all loans
- Update loan status

## Prerequisites

Before using the Loan API, ensure the following dependencies and tools are installed:

- Java 21 or higher
- Maven 3.6+
- A relational database (e.g., MySQL or PostgreSQL)
- Postman (optional, for testing endpoints)

## Installation and Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/aykutengin/loan-api.git
   cd loan-api
   ```

2. **Build the Application** Use Maven to build the project:

   ```bash
   mvn clean package
   ```

3. **Configure the Database** Update the `application.properties` file located in the `src/main/resources` directory with your database configuration:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/loan_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

4. **Run the Application** Start the application using the built JAR file:

   ```bash
   java -jar target/loan-api-0.0.1-SNAPSHOT.jar
   ```

   Alternatively, you can run it directly using Maven:

   ```bash
   mvn spring-boot:run
   ```

5. **Access the API** The application will be available at:

   ```
   http://localhost:8080
   ```

## API Endpoints

### Loan Endpoints

#### 1. Apply for a Loan

**POST** `/api/loans`

Request Body:

```json
{
  "customerName": "John Doe",
  "amount": 5000,
  "term": 12
}
```

Response:

```json
{
  "id": 1,
  "customerName": "John Doe",
  "amount": 5000,
  "term": 12,
  "status": "PENDING"
}
```

#### 2. Retrieve Loan Details

**GET** `/api/loans/{id}`

Response:

```json
{
  "id": 1,
  "customerName": "John Doe",
  "amount": 5000,
  "term": 12,
  "status": "APPROVED"
}
```

#### 3. List All Loans

**GET** `/api/loans`

Response:

```json
[
  {
    "id": 1,
    "customerName": "John Doe",
    "amount": 5000,
    "term": 12,
    "status": "APPROVED"
  },
  {
    "id": 2,
    "customerName": "Jane Smith",
    "amount": 10000,
    "term": 24,
    "status": "PENDING"
  }
]
```

#### 4. Update Loan Status

**PUT** `/api/loans/{id}`

Request Body:

````json
{
  "status": "APPROVED"
}
Response:
```json
{
  "id": 1,
  "customerName": "John Doe",
  "amount": 5000,
  "term": 12,
  "status": "APPROVED"
}
````
## Testing the API

- Use **Postman** or **curl** to test API endpoints.
- Example `curl` command to apply for a loan:
  ```bash
  curl -X POST http://localhost:8080/api/loans \
       -H "Content-Type: application/json" \
       -d '{"customerName": "John Doe", "amount": 5000, "term": 12}'
  ```

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch.
3. Make your changes.
4. Submit a pull request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

