
# Course & Student Management Application

This is a simple backend application designed to manage Courses and Students. It includes RESTful API endpoints for CRUD (Create, Read, Update, Delete) operations on both entities. The application is built using **Spring Boot**, **Java**, and **Spring Data JPA** for interacting with a relational database.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
  - [Course Endpoints](#course-endpoints)
  - [Student Endpoints](#student-endpoints)
- [Frontend](#frontend)
- [License](#license)

## Features

- Manage Courses (Create, Read, Update, Delete)
- Manage Students (Create, Read, Update, Delete)
- Associate students with courses (assign courses to students)
- Basic validation for incoming requests
- Simple error handling with HTTP status codes
- Basic frontend for interacting with the API

## Technologies

- **Spring Boot** - Backend framework
- **Spring Data JPA** - ORM for managing database interactions
- **Hibernate** - JPA implementation
- **H2/MySQL** - Default database (can be configured)
- **Java 17** - Programming language
- **HTML & JavaScript** - Simple frontend for API interaction

## Getting Started

### Prerequisites

- **Java 17** or later
- **Maven** (for managing dependencies and running the application)
- **MySQL** or any other database (if you prefer using a database other than H2)

### Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/course-student-management-app.git
    cd course-student-management-app
    ```

2. **Configure the Database**:
    - By default, the application uses an **H2 in-memory database**.
    - If you prefer using **MySQL**, you can modify the `application.properties` file:
    
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/your-database-name
      spring.datasource.username=your-username
      spring.datasource.password=your-password
      spring.jpa.hibernate.ddl-auto=update
      ```

3. **Build and run the application**:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

4. **Access the application**:
    - The application will start at `http://localhost:8080/`.

### Running the Application

Once the application is running, you can interact with the API through any HTTP client (like Postman or Curl) or via the **basic HTML frontend** included.

## API Endpoints

### Course Endpoints

- **Get All Courses**:  
  `GET /courses`

- **Get Course by ID**:  
  `GET /courses/{id}`

- **Create a New Course**:  
  `POST /courses`  
  Example JSON request body:
  ```json
  {
      "name": "Course Name",
      "description": "Course Description"
  }
  ```

- **Update a Course**:  
  `PUT /courses/{id}`  
  Example JSON request body:
  ```json
  {
      "name": "Updated Course Name",
      "description": "Updated Course Description"
  }
  ```

- **Delete a Course**:  
  `DELETE /courses/{id}`

### Student Endpoints

- **Get All Students**:  
  `GET /students`

- **Get Student by ID**:  
  `GET /students/{id}`

- **Create a New Student**:  
  `POST /students`  
  Example JSON request body:
  ```json
  {
      "firstName": "John",
      "lastName": "Doe",
      "email": "john.doe@example.com",
      "rollNo": "1234"
  }
  ```

- **Update a Student**:  
  `PUT /students/{id}`  
  Example JSON request body:
  ```json
  {
      "firstName": "John",
      "lastName": "Doe",
      "email": "john.doe@example.com",
      "rollNo": "1234"
  }
  ```

- **Delete a Student**:  
  `DELETE /students/{id}`

- **Assign Course to Student**:  
  `PUT /students/addCourse/{studentId}?courseid={courseId}`  
  (This endpoint allows you to assign a course to a student.)

## Frontend

A **basic HTML frontend** is provided for easy interaction with the application. You can interact with the API through simple forms that allow for creating, updating, and deleting both Courses and Students.

### How to Use the Frontend

1. Open the `index.html` file in a web browser.
2. Use the forms provided to interact with the API.
3. Results for each action (e.g., getting all students or creating a course) will be displayed on the page.

The frontend uses JavaScript `fetch()` to interact with the backend API. You can expand on the UI or integrate it into a full web application.



### Key Sections:
1. **Features**: Describes the core functionality of the application.
2. **Technologies**: Lists the tech stack used to build the project.
3. **Getting Started**: Provides step-by-step instructions to set up and run the application locally.
4. **API Endpoints**: Lists all available API endpoints for managing Courses and Students.
5. **Frontend**: Explains how to use the included HTML frontend for API interaction.
6. **License**: Optional section if the project is open-source.
