
# Booker App

Booker App is a backend application developed with Java Spring Boot. It is the graduation project of the Java backend academy organized by Sisterslab. This API provides features such as book recommendations, reviews, and reading lists for book lovers.


## Technologies 

- **Java 17:** Main programming language used for backend logic.
- **Spring Boot v3.3.3:** Used for rapid development of RESTful services.
- **Spring Data JPA:** A Spring module that simplifies database operations.
- **Spring Security:** A Spring module that manages security, authentication and authorization processes.
- **Maven:** Project dependency management and compilation operations.
- **Docker & Docker Compose:** Used to run the application and MySQL database as a container.
- **MySQL:** Used as a database management system.
- **Junit - Mockito:** Used for application unit testing.


## Getting Started

### Requirements

To run the project, you must have the following software installed on your system:

- Java 17 or later
- Maven 3.8 or later
- Docker and Docker Compose (optional)
- IntelliJ IDEA or another IDE
- MySQL or another compatible SQL database
- Swagger or Postman

### Installation

1. **Clone the project:**

   ```bash
   git clone https://github.com/irem-yigit/booker-app.git
   ```

2. **Configure the database:**

    - Create a database named `booker` (this step is not necessary if running with Docker).
    - Update the `application.properties` file in the `src/main/resources` folder according to your database information.
    
3. **Build the project with Maven:**

   ```bash
   mvn clean install
   ```

4. **Run the Spring Boot application:**

   ```bash
   mvn spring-boot:run
   ```

   Once the application is launched, you can start using the APIs.

5. **Running with Docker (optional):**

   To run the application with Docker, you can use the `docker-compose.yml` file located in the root directory of the project:

   ```bash
   docker-compose up --build
   ```

## API Test

   **Swagger URL:**

   ```bash
   http://localhost:8080/swagger-ui/index.html#/v3/api-docs
   ```

## API Endpoints

### The Booker App offers the following endpoints:

#### User Management

- `POST /api/register`           : Provides control over logging into the system.
- `GET /api/users/all`           : Gets a list of all users.
- `GET /api/users/{id}`          : Gets a specific user by id.
- `PUT /api/users/update/{id}`   : Provides the update of user information by id.
- `DELETE /api/users/delete/{id}`: Provides the deletion of a specific user by id.

![Goodreads-api-Swagger-user-controller](https://github.com/user-attachments/assets/06b07f6c-f03c-4ab5-843c-af321b29dbc6)
![Goodreads-api-Swagger-register-controller](https://github.com/user-attachments/assets/416757c0-3c83-464b-82cf-a55a64872450)

#### Book Management

- `POST /api/books/add`          : Provides the process of adding a new book.
- `GET /api/books/all`           : Brings the list of all books.
- `GET /api/books/id/{id}`       : Provides the ability to bring a specific book by id.
- `GET /api/books/isbn/{isbn}`   : Provides the ability to bring a specific book by isbn.
- `PUT /api/books/update/{id}`   : Provides the ability to update book information by id.
- `DELETE /api/books/delete/{id}`: Provides the ability to delete a specific book by id.
  
![Goodreads-api-Swagger-book-controller](https://github.com/user-attachments/assets/febb025b-06d3-45ed-8670-f00a357706a4)

#### BookShelf Management

- `POST /api/bookshelf/add`                             : Allows adding a new library.
- `GET /api/bookshelf/{username}/{type}`                       : Allows getting the user's book list.
- `POST /api/bookshelf/{bookshelfId}/books/{bookId}`    : Allows adding books to the reading list.
- `DELETE /api/bookshelf/{bookshelfId}/books/{bookId}`  : Allows deleting books from the reading list.

![Goodreads-api-Swagger-book-shelf-controller](https://github.com/user-attachments/assets/16c909a0-8178-4392-9cba-e4eb3fb4a039)

#### Schemas

![Goodreads-api-Swagger-Schemas-1](https://github.com/user-attachments/assets/6385ea7c-a861-4c3a-9070-fd264057ceed)
![Goodreads-api-Swagger-Schemas-2](https://github.com/user-attachments/assets/3a289396-6ee5-4ed9-a039-f6e936261d82)
