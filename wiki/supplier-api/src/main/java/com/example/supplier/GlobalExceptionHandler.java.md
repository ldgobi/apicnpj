# Documentation: `GlobalExceptionHandler.java`

## Overview
The `GlobalExceptionHandler` class is a centralized exception handling mechanism for a Spring Boot application. It uses the `@ControllerAdvice` annotation to intercept and handle exceptions thrown by controllers globally. This approach ensures a consistent response structure for various types of exceptions.

## Features
- Handles specific exceptions (`ConstraintViolationException`, `IllegalArgumentException`) and provides meaningful HTTP responses.
- Catches generic exceptions (`Exception`) to prevent unhandled errors from propagating.
- Returns structured error responses using the `ErrorResponse` class (assumed to be defined elsewhere in the application).

## Annotations and Dependencies
| Annotation/Dependency                | Purpose                                                                 |
|--------------------------------------|-------------------------------------------------------------------------|
| `@ControllerAdvice`                  | Marks the class as a global exception handler for all controllers.      |
| `@ExceptionHandler`                  | Specifies the type of exception to handle for each method.              |
| `ResponseEntity`                     | Represents the HTTP response, including status code and body.           |
| `HttpStatus`                         | Provides standard HTTP status codes for responses.                      |
| `ConstraintViolationException`       | Handles validation errors (e.g., from `jakarta.validation`).            |

## Exception Handling Methods

### 1. `handleConstraintViolationException`
Handles exceptions of type `ConstraintViolationException`, typically thrown during validation failures.

- **Input**: `ConstraintViolationException`
- **Response**:
  - **HTTP Status**: `400 BAD_REQUEST`
  - **Body**: An `ErrorResponse` object containing:
    - Status: `400 BAD_REQUEST`
    - Message: A detailed validation error message.

```java
@ExceptionHandler(ConstraintViolationException.class)
public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), "Validation error: " + ex.getMessage()));
}
```

---

### 2. `handleIllegalArgumentException`
Handles exceptions of type `IllegalArgumentException`, which are typically thrown when invalid arguments are passed to methods.

- **Input**: `IllegalArgumentException`
- **Response**:
  - **HTTP Status**: `400 BAD_REQUEST`
  - **Body**: An `ErrorResponse` object containing:
    - Status: `400 BAD_REQUEST`
    - Message: The exception's message.

```java
@ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
    return new ResponseEntity<ErrorResponse>(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), ex.getMessage()),
            HttpStatus.BAD_REQUEST);
}
```

---

### 3. `handleException`
Handles all other exceptions that are not explicitly handled by the above methods. This acts as a fallback mechanism.

- **Input**: `Exception`
- **Response**:
  - **HTTP Status**: `500 INTERNAL_SERVER_ERROR`
  - **Body**: An `ErrorResponse` object containing:
    - Status: `500 INTERNAL_SERVER_ERROR`
    - Message: A generic error message with the exception's details.
- **Additional Behavior**: Prints the stack trace of the exception to the console for debugging purposes.

```java
@ExceptionHandler(Exception.class)
public ResponseEntity<ErrorResponse> handleException(Exception e) {
    e.printStackTrace();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    "An error occurred: " + e.getMessage()));
}
```

---

## Insights
- **Centralized Error Handling**: The use of `@ControllerAdvice` ensures that all exceptions are handled in a single place, promoting cleaner and more maintainable code.
- **Custom Error Response**: The `ErrorResponse` class (assumed to exist) standardizes the structure of error messages, making it easier for clients to parse and understand errors.
- **Validation and Debugging**: Specific handling for `ConstraintViolationException` and `IllegalArgumentException` ensures that validation errors are communicated clearly. The fallback handler (`handleException`) provides a safety net for unexpected errors while logging them for debugging.
- **HTTP Status Codes**: The class adheres to RESTful principles by returning appropriate HTTP status codes (`400` for client errors, `500` for server errors).

## Assumptions
- The `ErrorResponse` class is defined elsewhere in the application and contains at least two fields: `status` and `message`.
- The application uses `jakarta.validation` for input validation, which can throw `ConstraintViolationException`.
