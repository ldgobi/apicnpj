# Documentation: `GlobalExceptionHandler.java`

## Overview
The `GlobalExceptionHandler` class is a centralized exception handling mechanism for a Spring Boot application. It uses the `@ControllerAdvice` annotation to intercept exceptions thrown by controllers and provide custom responses. This approach ensures consistent error handling across the application.

## Features
- Handles specific exceptions (`ConstraintViolationException`, `IllegalArgumentException`) and provides tailored responses.
- Provides a fallback mechanism for handling generic exceptions (`Exception`).
- Returns HTTP responses with appropriate status codes and error messages.

## Class Details

### Annotations
- **`@ControllerAdvice`**: Indicates that this class provides global exception handling for controllers.
- **`@ExceptionHandler`**: Specifies the type of exception to handle and maps it to a method.

### Methods

| Method Name                              | Exception Type                  | HTTP Status Code         | Description                                                                 |
|------------------------------------------|---------------------------------|--------------------------|-----------------------------------------------------------------------------|
| `handleConstraintViolationException`     | `ConstraintViolationException` | `400 BAD_REQUEST`       | Handles validation errors caused by constraint violations.                 |
| `handleIllegalArgumentException`         | `IllegalArgumentException`     | `400 BAD_REQUEST`       | Handles errors caused by invalid arguments passed to methods.              |
| `handleException`                        | `Exception`                    | `500 INTERNAL_SERVER_ERROR` | Handles all other uncaught exceptions and logs the stack trace.            |

### Error Response Structure
The methods return an `ErrorResponse` object, which encapsulates the following information:
- **Status Code**: The HTTP status code as a string.
- **Message**: A descriptive error message.

## Insights
- **Centralized Error Handling**: By using `@ControllerAdvice`, the application avoids repetitive exception handling code in individual controllers.
- **Custom Error Messages**: The error messages are dynamically generated based on the exception details, improving debugging and user feedback.
- **Scalability**: Additional exception handlers can be added to this class to handle new types of exceptions as the application evolves.
- **Logging**: The `handleException` method logs the stack trace of generic exceptions, aiding in debugging and monitoring.

## Dependencies
- **Spring Framework**: Provides annotations like `@ControllerAdvice` and `@ExceptionHandler`.
- **Jakarta Validation**: Used for handling `ConstraintViolationException`.

## File Metadata
- **File Name**: `GlobalExceptionHandler.java`
