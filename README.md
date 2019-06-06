# TransactionModule
This module exposes a bank transfer RESTful endpoint, for now.

## Technology stack
- Java
- Hibernate
- H2 In-memory database
- Embedded Jersey Server (Microservice)
- RESTful web service

## Getting started
Clone the repo, start application with `mvn clean exec:java` command, enter following URL:

`http://localhost:8080/revolut/transaction/transfer?recipientAccountNumber=0&senderAccountNumber=1&amount=100`

Where:

|Param                 |Description                    |
|----------------------|-------------------------------|
|recipientAccountNumber|recipient account number       |
|senderAccountNumber   |sender account number          |
|amount                |amount to be transferred       |

**Note:** For testing purposes this application creates two users when initializing database as follows

|Account number |Initial balance |
|---------------|----------------|
|0              |1000            |
|1              |1000            |
