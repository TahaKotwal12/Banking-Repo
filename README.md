# Banking Application - Spring Boot Project Structure

## Overview

This is a modern Spring Boot REST API application for the Presto Online Banking System. It follows industry-standard layered architecture and best practices.

---

## 📁 Project Structure

```
application/
├── src/
│   ├── main/
│   │   ├── java/com/bank/application/
│   │   │   ├── Application.java                    # Main Spring Boot application class
│   │   │   │
│   │   │   ├── config/                             # Configuration classes
│   │   │   │   ├── SecurityConfig.java             # Spring Security configuration
│   │   │   │   ├── JwtConfig.java                  # JWT configuration
│   │   │   │   ├── WebConfig.java                  # Web MVC configuration
│   │   │   │   ├── CorsConfig.java                 # CORS configuration
│   │   │   │   └── SwaggerConfig.java              # API documentation config
│   │   │   │
│   │   │   ├── controller/                         # REST API Controllers
│   │   │   │   ├── AuthenticationController.java   # /api/auth/*
│   │   │   │   ├── ClientController.java           # /api/clients/*
│   │   │   │   ├── EmployeeController.java         # /api/employees/*
│   │   │   │   ├── TransactionController.java      # /api/transactions/*
│   │   │   │   ├── BankDetailsController.java      # /api/bank-details/*
│   │   │   │   └── AdminController.java            # /api/admin/*
│   │   │   │
│   │   │   ├── dto/                                # Data Transfer Objects
│   │   │   │   ├── request/                        # Request DTOs
│   │   │   │   │   ├── LoginRequest.java
│   │   │   │   │   ├── CreateClientRequest.java
│   │   │   │   │   ├── UpdateClientRequest.java
│   │   │   │   │   ├── CreateEmployeeRequest.java
│   │   │   │   │   ├── DepositRequest.java
│   │   │   │   │   ├── WithdrawalRequest.java
│   │   │   │   │   └── ChangePasswordRequest.java
│   │   │   │   │
│   │   │   │   └── response/                       # Response DTOs
│   │   │   │       ├── ApiResponse.java            # ✅ Generic response wrapper
│   │   │   │       ├── ErrorDetail.java            # ✅ Error detail class
│   │   │   │       ├── PageResponse.java           # ✅ Pagination wrapper
│   │   │   │       ├── LoginResponse.java
│   │   │   │       ├── ClientDTO.java
│   │   │   │       ├── EmployeeDTO.java
│   │   │   │       ├── TransactionDTO.java
│   │   │   │       └── BankDetailsDTO.java
│   │   │   │
│   │   │   ├── entity/                             # JPA Entities
│   │   │   │   ├── BankAdminLogin.java
│   │   │   │   ├── BankAdminLoginman.java
│   │   │   │   ├── BankEmpDetails.java
│   │   │   │   ├── BankEmpLogin.java
│   │   │   │   ├── BankEmpLoginman.java
│   │   │   │   ├── BankClientDetails.java
│   │   │   │   ├── BankClientLogin.java
│   │   │   │   ├── BankClientLoginman.java
│   │   │   │   ├── BankTrans.java
│   │   │   │   └── BankDetails.java
│   │   │   │
│   │   │   ├── repository/                         # Spring Data JPA Repositories
│   │   │   │   ├── BankAdminLoginRepository.java
│   │   │   │   ├── BankAdminLoginmanRepository.java
│   │   │   │   ├── BankEmpDetailsRepository.java
│   │   │   │   ├── BankEmpLoginRepository.java
│   │   │   │   ├── BankEmpLoginmanRepository.java
│   │   │   │   ├── BankClientDetailsRepository.java
│   │   │   │   ├── BankClientLoginRepository.java
│   │   │   │   ├── BankClientLoginmanRepository.java
│   │   │   │   ├── BankTransRepository.java
│   │   │   │   └── BankDetailsRepository.java
│   │   │   │
│   │   │   ├── service/                            # Service Interfaces
│   │   │   │   ├── AuthenticationService.java
│   │   │   │   ├── ClientService.java
│   │   │   │   ├── EmployeeService.java
│   │   │   │   ├── TransactionService.java
│   │   │   │   ├── BankDetailsService.java
│   │   │   │   └── AdminService.java
│   │   │   │
│   │   │   ├── service/impl/                       # Service Implementations
│   │   │   │   ├── AuthenticationServiceImpl.java
│   │   │   │   ├── ClientServiceImpl.java
│   │   │   │   ├── EmployeeServiceImpl.java
│   │   │   │   ├── TransactionServiceImpl.java
│   │   │   │   ├── BankDetailsServiceImpl.java
│   │   │   │   └── AdminServiceImpl.java
│   │   │   │
│   │   │   ├── security/                           # Security Components
│   │   │   │   ├── JwtTokenService.java            # JWT token generation/validation
│   │   │   │   ├── JwtAuthenticationFilter.java    # JWT authentication filter
│   │   │   │   ├── UserDetailsServiceImpl.java     # Custom UserDetailsService
│   │   │   │   └── PasswordEncoderConfig.java      # Password encoder bean
│   │   │   │
│   │   │   ├── exception/                          # Custom Exceptions
│   │   │   │   ├── GlobalExceptionHandler.java     # ✅ Global exception handler
│   │   │   │   ├── ResourceNotFoundException.java  # ✅
│   │   │   │   ├── DuplicateResourceException.java # ✅
│   │   │   │   ├── InvalidCredentialsException.java # ✅
│   │   │   │   ├── InsufficientBalanceException.java # ✅
│   │   │   │   ├── InvalidTokenException.java      # ✅
│   │   │   │   └── UnauthorizedException.java      # ✅
│   │   │   │
│   │   │   └── util/                               # Utility Classes
│   │   │       ├── Constants.java                  # ✅ Application constants
│   │   │       ├── DateUtils.java
│   │   │       ├── ValidationUtils.java
│   │   │       └── PasswordUtils.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties              # Application configuration
│   │       ├── application-dev.properties          # Development profile
│   │       ├── application-prod.properties         # Production profile
│   │       ├── static/                             # Static resources
│   │       └── templates/                          # Templates (if needed)
│   │
│   └── test/
│       └── java/com/bank/application/
│           ├── controller/                         # Controller tests
│           ├── service/                            # Service tests
│           ├── repository/                         # Repository tests
│           └── integration/                        # Integration tests
│
├── pom.xml                                         # Maven configuration
├── README.md                                       # This file
└── .gitignore                                      # Git ignore rules
```

---

## ✅ Completed Components

### 1. Response Wrapper Classes
- **ApiResponse.java** - Generic response wrapper for all API responses
- **ErrorDetail.java** - Detailed error information for validation errors
- **PageResponse.java** - Pagination wrapper for list endpoints

### 2. Exception Handling
- **GlobalExceptionHandler.java** - Centralized exception handling
- **Custom Exceptions** - All business-specific exceptions created

### 3. Utilities
- **Constants.java** - Application-wide constants

### 4. Package Structure
- All packages created with documentation
- Proper separation of concerns
- Industry-standard layered architecture

---

## 🎯 ApiResponse Usage Examples

### Success Response
```java
@GetMapping("/{id}")
public ResponseEntity<ApiResponse<ClientDTO>> getClient(@PathVariable String id) {
    ClientDTO client = clientService.getClientById(id);
    return ResponseEntity.ok(ApiResponse.success("Client retrieved successfully", client));
}
```

**Response**:
```json
{
  "status": "success",
  "message": "Client retrieved successfully",
  "data": {
    "id": "1234",
    "firstName": "John",
    "lastName": "Doe",
    ...
  },
  "statusCode": 200,
  "timestamp": "2025-12-30T11:36:18"
}
```

### Error Response
```java
@PostMapping
public ResponseEntity<ApiResponse<ClientDTO>> createClient(@Valid @RequestBody CreateClientRequest request) {
    // If validation fails, GlobalExceptionHandler will catch it
    ClientDTO client = clientService.createClient(request);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.success("Client created successfully", client, 201));
}
```

**Validation Error Response**:
```json
{
  "status": "error",
  "message": "Invalid input provided",
  "errors": [
    {
      "field": "username",
      "message": "Username must be between 3 and 50 characters",
      "rejectedValue": "ab"
    },
    {
      "field": "email",
      "message": "Invalid email format",
      "rejectedValue": "invalid-email"
    }
  ],
  "statusCode": 400,
  "path": "/api/clients",
  "timestamp": "2025-12-30T11:36:18"
}
```

### Paginated Response
```java
@GetMapping
public ResponseEntity<ApiResponse<PageResponse<ClientDTO>>> getAllClients(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
    
    Page<ClientDTO> clientPage = clientService.getAllClients(page, size);
    PageResponse<ClientDTO> pageResponse = PageResponse.of(clientPage);
    
    return ResponseEntity.ok(
        ApiResponse.success("Clients retrieved successfully", pageResponse)
    );
}
```

**Response**:
```json
{
  "status": "success",
  "message": "Clients retrieved successfully",
  "data": {
    "content": [...],
    "pageNumber": 0,
    "pageSize": 20,
    "totalElements": 150,
    "totalPages": 8,
    "first": true,
    "last": false,
    "hasNext": true,
    "hasPrevious": false
  },
  "statusCode": 200,
  "timestamp": "2025-12-30T11:36:18"
}
```

---

## 🔧 Technology Stack

- **Spring Boot**: 3.5.8
- **Java**: 21
- **Spring Data JPA**: For database access
- **Spring Security**: For authentication & authorization
- **MySQL**: Database
- **Lombok**: Reduce boilerplate code
- **Maven**: Build tool

---

## 📝 Coding Standards

### 1. Naming Conventions
- **Classes**: PascalCase (e.g., `ClientService`)
- **Methods**: camelCase (e.g., `getClientById`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `MAX_PAGE_SIZE`)
- **Packages**: lowercase (e.g., `com.bank.application.service`)

### 2. Response Standards
- **All responses** must be wrapped in `ApiResponse<T>`
- **Success responses** use `ApiResponse.success()`
- **Error responses** use `ApiResponse.error()`
- **HTTP status codes** must be set appropriately

### 3. Exception Handling
- Use custom exceptions for business logic errors
- Let `GlobalExceptionHandler` handle all exceptions
- Provide meaningful error messages
- Include field-level errors for validation

### 4. Validation
- Use `@Valid` annotation for request validation
- Use Jakarta validation annotations (`@NotNull`, `@Size`, etc.)
- Custom validators for complex validation logic

---

## 🚀 Next Steps

### Phase 1: Entity Layer (To Do)
1. Create all JPA entity classes
2. Define relationships (@OneToOne, @OneToMany, etc.)
3. Add proper annotations (@Entity, @Table, @Column)

### Phase 2: Repository Layer (To Do)
1. Create Spring Data JPA repositories
2. Add custom query methods
3. Add @Query annotations where needed

### Phase 3: Service Layer (To Do)
1. Create service interfaces
2. Implement service classes
3. Add business logic
4. Add transaction management

### Phase 4: Controller Layer (To Do)
1. Create REST controllers
2. Define API endpoints
3. Add request/response DTOs
4. Add validation

### Phase 5: Security (To Do)
1. Configure Spring Security
2. Implement JWT authentication
3. Add authentication filter
4. Configure authorization rules

### Phase 6: Testing (To Do)
1. Unit tests for services
2. Integration tests for repositories
3. API tests for controllers

---

## 📖 Documentation

For detailed documentation, see:
- [Architecture Overview](../../docs/01-architecture-overview.md)
- [API Documentation](../../docs/05-api-documentation.md)
- [Authentication Flow](../../docs/10-authentication-flow-hinglish.md)
- [Deployment Guide](../../docs/07-deployment-guide.md)

---

## 👥 Contributors

- Development Team
- Architecture Team
- QA Team

---

## 📄 License

Proprietary - Presto Bank

---

**Last Updated**: December 30, 2025
#   B a n k i n g - R e p o  
 