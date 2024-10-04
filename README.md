# Goodreads App

Goodreads App is a backend application developed with Java Spring Boot. It is the graduation project of the Java backend academy organized by Sisterslab. This API provides features such as book recommendations, reviews, and reading lists for book lovers.


## Technologies 

- **Java 17:** Main programming language used for backend logic.
- **Spring Boot:** Used for rapid development of RESTful services.
- **Spring Data JPA:** A Spring module that simplifies database operations.
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

### Installation

1. **Clone the project:**

   ```bash
   git clone https://github.com/irem-yigit/goodreads-app.git
   ```

2. **Configure the database:**

    - Create a database named `goodreads` (this step is not necessary if running with Docker).
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

## API Endpoints

### The Goodreads App offers the following endpoints:

#### User Management

- `POST /api/register`           : Provides control over logging into the system.
- `GET /api/users/all`           : Gets a list of all users.
- `GET /api/users/{id}`          : Gets a specific user by id.
- `PUT /api/users/update/{id}`   : Provides the update of user information by id.
- `DELETE /api/users/delete/{id}`: Provides the deletion of a specific user by id.

#### Book Management

- `POST /api/books/add`          : Provides the process of adding a new book.
- `GET /api/books/all`           : Brings the list of all books.
- `GET /api/books/id/{id}`       : Provides the ability to bring a specific book by id.
- `GET /api/books/isbn/{isbn}`   : Provides the ability to bring a specific book by isbn.
- `PUT /api/books/update/{id}`   : Provides the ability to update book information by id.
- `DELETE /api/books/delete/{id}`: Provides the ability to delete a specific book by id.

#### BookShelf Management

- `POST /api/bookshelf/add`                             : Allows adding a new library.
- `GET /api/bookshelf/{username}`                       : Allows getting the user's book list.
- `POST /api/bookshelf/{bookshelfId}/books/{bookId}`    : Allows adding books to the reading list.
- `DELETE /api/bookshelf/{readingListId}/books/{bookId}`: Allows deleting books from the reading list.