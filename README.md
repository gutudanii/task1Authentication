Here’s the complete **`README.md`** content tailored to your project:

---

# **Task1SecureAuthentication**

This project is part of the **Prodigy Infotech Internship Program** and demonstrates a secure authentication system using **Spring Boot**. The system implements features like **JWT-based authentication**, **role-based access control**, and **OTP verification** for user registration.

---

## **🛠️ Tech Stack**

- **Java 17**
- **Spring Boot**
  - `spring-boot-starter-security`
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-mail`
  - `spring-boot-starter-validation`
- **PostgreSQL**
  - `postgresql`
- **JWT**
  - `jjwt-api`
  - `jjwt-impl`
  - `jjwt-jackson`
- **Utilities**
  - `lombok`
- **Build Tool**
  - `maven`

---

## **📂 Folder Structure**

```
src/
├── main/
│   ├── java/
│   │   └── com.prodigyinfotech.task1SecureAuthentication/
│   │       ├── controller/       # Handles API endpoints
│   │       │   ├── UserController.java
│   │       │   └── OtpController.java
│   │       ├── dto/              # Data Transfer Objects
│   │       │   ├── request/
│   │       │   │   ├── UserRegisterRequestDTO.java
│   │       │   │   └── UserLoginRequestDTO.java
│   │       │   ├── response/
│   │       │   │   ├── UserInfoResponseDTO.java
│   │       │   │   └── MessageDTO.java
│   │       ├── model/            # Entity classes for database
│   │       │   ├── Users.java
│   │       │   ├── Otp.java
│   │       │   └── User_Role.java
│   │       ├── repository/       # Database access layer
│   │       │   ├── UserRepository.java
│   │       │   └── OtpRepository.java
│   │       ├── security/         # Security configurations and filters
│   │       │   ├── jwt/
│   │       │   │   ├── JwtAuthenticationEntryPoint.java
│   │       │   │   ├── JwtRequestFilter.java
│   │       │   │   └── JwtUtil.java
│   │       │   ├── CorsConfig.java
│   │       │   └── SecurityConfig.java
│   │       ├── service/          # Business logic layer
│   │       │   ├── impl/
│   │       │   │   ├── UserServiceImpl.java
│   │       │   │   └── OtpService.java
│   │       │   └── UserService.java
│   │       ├── util/             # Utility classes
│   │       │   ├── PasswordConfig.java
│   │       │   └── JwtUtil.java
│   │       └── Task1SecureAuthenticationApplication.java  # Main entry point
│   ├── resources/
│   │   ├── static/               # Static files (CSS, JS, Images)
│   │   ├── templates/            # HTML templates (if applicable)
│   │   └── application.properties # Application configuration
├── test/                         # Test cases
│   └── ...
├── pom.xml                       # Maven dependencies and build configurations
└── README.md                     # Project documentation
```

---

## **🌟 Features**

### **Authentication and Authorization**
- **JWT-based authentication** for secure access to APIs.
- Role-based access control with `User_Role` entities.
- Passwords stored using a **BCrypt** encoder.

### **User Registration with OTP**
- Email-based OTP verification for secure user registration.
- Seamless integration with `spring-boot-starter-mail`.

### **API Security**
- Cross-Origin Resource Sharing (CORS) handled via `CorsConfig`.
- CSRF protection disabled for stateless APIs.

### **Scalable Design**
- Clean and modular folder structure ensures maintainability.
- Business logic is separated into services for better scalability.

---

## **🚀 Getting Started**

### **1. Clone the Repository**
```bash
git clone https://github.com/your-username/task1SecureAuthentication.git
cd task1SecureAuthentication
```

### **2. Configure the Application**
Update the `application.properties` file in the `resources/` directory with your database credentials and email settings:
```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password

# Mail Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email
spring.mail.password=your_email_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### **3. Build and Run**
```bash
mvn spring-boot:run
```

### **4. Access the Application**
- API endpoints are available at `http://localhost:8080/`.
- Swagger (if added) can be accessed at `http://localhost:8080/swagger-ui/`.

---

## **📖 API Endpoints**

### **User Endpoints**
- `POST /api/users/register`: Register a new user with OTP verification.
- `POST /api/users/login`: Login with email and password.
- `GET /api/users/{id}`: Fetch user details by ID.

### **OTP Endpoints**
- `POST /api/otp/verify`: Verify the OTP sent to the user.

---

## **🤝 Contributing**
Contributions are welcome! Feel free to fork the repository, create a branch, and submit a pull request.

---

## **📜 License**
This project is licensed under the MIT License.

---

## **📧 Contact**
For any inquiries or support, reach out at `gutudanielgeleta.g@gmail.com`.

---
