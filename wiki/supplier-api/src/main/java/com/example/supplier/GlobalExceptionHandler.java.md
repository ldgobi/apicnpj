# Documentation: `GlobalExceptionHandler.java`

## Overview
The `GlobalExceptionHandler` class is a centralized exception handling mechanism for a Spring Boot application. It uses the `@ControllerAdvice` annotation to intercept and handle exceptions thrown by controllers globally. This approach ensures consistent error responses across the application.

## Features
- Handles specific exceptions such as `ConstraintViolationException` and `IllegalArgumentException`.
- Provides a generic handler for all other exceptions.
- Returns structured error responses with HTTP status codes and descriptive messages.

## Class Details

### Annotations
- **`@ControllerAdvice`**: Indicates that this class provides global exception handling for controllers.
- **`@ExceptionHandler`**: Specifies the type of exception to be handled by each method.

### Methods

| Method Name                              | Exception Type                  | HTTP Status Code         | Description                                                                 |
|------------------------------------------|----------------------------------|--------------------------|-----------------------------------------------------------------------------|
| `handleConstraintViolationException`     | `ConstraintViolationException`  | `HttpStatus.BAD_REQUEST` | Handles validation errors caused by constraint violations.                 |
| `handleIllegalArgumentException`         | `IllegalArgumentException`      | `HttpStatus.BAD_REQUEST` | Handles errors caused by invalid arguments passed to methods.              |
| `handleException`                        | `Exception`                     | `HttpStatus.INTERNAL_SERVER_ERROR` | Handles all other uncaught exceptions and logs the stack trace.            |

### Error Response Structure
The error response returned by the exception handlers is encapsulated in the `ErrorResponse` class. It includes:
- **`status`**: The HTTP status code as a string.
- **`message`**: A descriptive error message.

## Insights
- **Centralized Exception Handling**: This class simplifies error management by consolidating exception handling logic in one place, reducing redundancy in controller classes.
- **Custom Error Responses**: The use of the `ErrorResponse` class ensures that error messages are consistently formatted, improving API usability for clients.
- **Scalability**: Additional exception handlers can be added to this class to handle new types of exceptions as the application evolves.
- **Logging**: The `handleException` method logs the stack trace of uncaught exceptions, aiding in debugging and monitoring.

## Dependencies
- **Spring Framework**: Provides annotations like `@ControllerAdvice` and `@ExceptionHandler`.
- **Jakarta Validation**: Used for handling `ConstraintViolationException`.

## File Metadata
- **File Name**: `GlobalExceptionHandler.java`
