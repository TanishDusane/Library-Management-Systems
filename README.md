# Library Management System Project

## Introduction

This is a Library Management System designed to manage books, library cards, authors, students, and transactions in a library setting. It provides functionality for adding books, issuing books to library members, managing library cards, tracking transactions, managing authors, and managing students.

## Features

- Add, update, or delete books in the library inventory.
- Issue books to library members and track transactions.
- Manage library cards for library members.
- Track the number of books issued to each library card.
- Validate library card expiration dates before issuing books.
- Manage authors and their information.
- Manage students and their information.

## Technologies Used

- Java (Version 17)
- Spring Boot (Version 3.2.4)
- Spring Data JPA
- MySQL (or your preferred database)
- Maven (Apache Maven 3.9.3)
- Git (2.44)

## Getting Started

To get started with this project, follow these steps:

1. Clone the repository to your local machine using the following command:
   
   ```
   git clone https://github.com/TanishDusane/Library-Management-Systems.git
   ```

3. Set up your development environment with Java and Maven.
4. Configure your database connection in the application properties file.
5. Run the application using Maven or your preferred IDE.

## Usage

Once the application is up and running, you can use the provided API endpoints to perform various operations. You can test these endpoints using tools like Postman.

### API Endpoints:

- `/books`: Add, update, or delete books in the library inventory.
- `/cards`: Manage library cards for library members.
- `/transactions`: Issue books to library members and track transactions.
- `/authors`: Manage authors and their information.
- `/students`: Manage students and their information.

Refer to the API documentation or code comments for more details on available endpoints and their usage.

## Testing with Postman

You can use Postman to test the API endpoints. Import the provided Postman collection and environment files for easy testing.

[Postman Collection](link-to-postman-collection-file)
[Postman Environment](link-to-postman-environment-file)

## Contributors

- [Tanish Dusane](https://github.com/TanishDusane/TanishDusane)

---

Happy coding!
