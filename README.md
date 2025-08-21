The backend of this Job Portal is built using Spring Boot and provides REST APIs for job management, authentication, and application tracking. It connects to a PostgreSQL database and handles role-based access (Admin/User).

#Tech Stack

Spring Boot (REST APIs)

Spring Security (authentication & role-based access)

Hibernate / JPA (ORM for database interaction)

PostgreSQL (database)

Maven (build tool)

#Features

User registration & login with role (User/Admin)

Admins can create, edit, and delete job posts

Users can view and apply for jobs with resume upload

Admins can view applicants for their own job posts

#Setup Instructions

Install Java 17+ and Maven.

Clone the repository.

Navigate to the backend folder:

cd backend


#Configure PostgreSQL in application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/jobportal
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update


#Run the application:

mvn spring-boot:run


#Backend will start at: http://localhost:8080

#API Endpoints (examples)

POST /register → Register user/admin

POST /login → Login and receive JWT token

GET /jobPosts → View all jobs

POST /jobPost → Create job (Admin only)

GET /admin/applications/{username}/{postId} → View applicants for a job
