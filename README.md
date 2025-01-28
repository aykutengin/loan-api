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

3. **Run the Application** Start the application using the built JAR file:

   ```bash
   java -jar target/loan-api-0.0.1-SNAPSHOT.jar
   ```

   Alternatively, you can run it directly using Maven:

   ```bash
   mvn spring-boot:run
   ```

4. **Access the API** The application will be available at:

   ```
   http://localhost:8080
   ```

## API Endpoints

### Loan Endpoints

#### 1. Apply for a Loan

**POST** `/api/createLoan`

Request Body:

```json
{  
   "customerId": 1,
   "amount": 4500,
   "interestRate": 0.1,
   "numberOfInstallments": 9
}
```

Response:

```bash
Loan created successfully!
```

#### 2. Retrieve Loan Details

**GET** `/api/v1/listInstallment/{loanId}`

Response:

```json
[
   {
      "id": 1,
      "loanId": 1,
      "amount": 500,
      "paidAmount": 500,
      "dueDate": "2024-02-01",
      "paymentDate": "2024-02-01",
      "paid": true
   },
   {
      "id": 2,
      "loanId": 1,
      "amount": 500,
      "paidAmount": 500,
      "dueDate": "2024-03-01",
      "paymentDate": "2024-03-01",
      "paid": true
   },
   {
      "id": 3,
      "loanId": 1,
      "amount": 500,
      "paidAmount": 500,
      "dueDate": "2024-04-01",
      "paymentDate": "2024-04-01",
      "paid": true
   },
   {
      "id": 4,
      "loanId": 1,
      "amount": 500,
      "paidAmount": 500,
      "dueDate": "2024-05-01",
      "paymentDate": "2024-05-01",
      "paid": true
   },
   {
      "id": 5,
      "loanId": 1,
      "amount": 500,
      "paidAmount": 500,
      "dueDate": "2024-06-01",
      "paymentDate": "2024-06-01",
      "paid": true
   },
   {
      "id": 6,
      "loanId": 1,
      "amount": 500,
      "paidAmount": 0,
      "dueDate": "2024-07-01",
      "paymentDate": null,
      "paid": false
   }
]
```

#### 3. List All Loans

**GET** `/api/v1/loans{customerId}`

Response:

```json
[
   {
      "id": 1,
      "customerId": 1,
      "loanAmount": 3000,
      "numberOfInstallment": 6,
      "createDate": "2024-01-01",
      "paid": false
   }
]
```

#### 4. Pay Loan

**PUT** `/api/v1/loans/{loanId}{amount}`

Response:
```json
{
   "isPaidCompletely": "true",
   "paidInstallmentCount": "1",
   "totalAmountSpent": "500.0"
}
````
## Testing the API

- Use **Swagger** to test API endpoints.
- Access it from http://localhost:8080/swagger-ui/index.html

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch.
3. Make your changes.
4. Submit a pull request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

