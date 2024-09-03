Employee Management System

Overview

The Employee Management System is a distributed application built using Spring Boot and microservices architecture. It allows administrators to manage employees, departments, tasks, and performance, while employees can manage their profiles, view tasks, and track performance. Table of Contents

Technologies Used Problem Statement Microservices Architecture Project Flow Setup Instructions API Endpoints Testing Future Enhancements Contributing License Technologies Used

Programming Language: Java Frameworks: Spring Boot, Spring Cloud, Spring Data JPA Database: MySQL/MariaDB API Design: RESTful APIs Service Registry: Eureka Server API Gateway: Spring Cloud Gateway Authentication: JWT Problem Statement

Develop a scalable Employee Management System using microservices architecture, where:

Admin: Can manage employees, departments, tasks, and performance reviews. Employees: Can manage their profiles, view assigned tasks, and track their performance. Microservices Architecture

The system is divided into multiple microservices, each handling a specific domain:

Service Registry & Discovery: Eureka Server for dynamic service discovery. API Gateway: Spring Cloud Gateway for routing and securing API requests. Authentication Service: Manages user authentication and authorization using JWT. Employee Management Microservice: CRUD operations for employee data. Department Management Microservice: CRUD operations for department data. Task Management Microservice: Handles task creation, assignment, and tracking. Performance Management Microservice: Tracks and evaluates employee performance. Project Flow

Admin Module: Access to dashboards, employee management, department management, task management, and performance evaluation. Employee Module: Allows employees to manage personal profiles, view tasks, and track their performance. Setup Instructions

To set up and run the Employee Management System locally, follow these steps:

Clone the Repository: git clone https://github.com/your-username/employee-management-system.git

Navigate to the Project Directory:

cd employee-management-system

Set Up the Database:

Ensure MySQL is installed and running. Create a database named capstone_project_db. Update the database configuration in application.properties if necessary. Build the Project:

mvn clean install

Run the Services:

Start Eureka Server. Run each microservice independently using the Spring Boot application runner or via command line:

mvn spring-boot:run
Access the Application: API Gateway: http://localhost:8181 Eureka Dashboard: http://localhost:8762 API Endpoints

Here are some of the primary API endpoints:

Employee Management: GET /api/employees - Get all employees POST /api/employees - Add a new employee PUT /api/employees/{id} - Update employee information DELETE /api/employees/{id} - Delete an employee

Department Management: GET /api/departments - Get all departments POST /api/departments - Create a new department PUT /api/departments/{id} - Update department information DELETE /api/departments/{id} - Delete a department

Task Management: GET /api/tasks - Get all tasks POST /api/tasks - Create a new task PUT /api/tasks/{id} - Update task information DELETE /api/tasks/{id} - Delete a task

Performance Management: GET /api/performance - Get performance metrics POST /api/performance - Create a performance review Refer to the Swagger documentation for a full list of endpoints and their details. Testing

Unit Testing: Implemented using JUnit and Mockito to test individual components. Integration Testing: Ensures that microservices interact correctly. End-to-End Testing: Conducted to validate the complete workflow of the application. Future Enhancements

Advanced Analytics: Introduce detailed analytics and reporting features. Role-Based Access Control: More granular control over access permissions. Mobile App Integration: Develop mobile apps for both Admins and Employees. Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any improvements, bug fixes, or feature requests. License

This project is licensed under the MIT License. See the LICENSE file for details.

For questions or feedback, feel free to reach out to:

Author: Nikita Shinde  Email: shindenikita413512@gmail.com
