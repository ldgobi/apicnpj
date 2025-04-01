# Documentation: `ErrorResponse.java`

## Overview
The `ErrorResponse` class is a simple data structure designed to encapsulate error-related information. It provides a way to represent error codes and their corresponding messages, which can be useful for error handling in applications.

---

## Class Details

### Class Name
`ErrorResponse`

### Package
`com.example.supplier`

---

## Attributes

| Attribute Name | Type     | Description                          |
|----------------|----------|--------------------------------------|
| `code`         | `String` | Represents the error code.          |
| `message`      | `String` | Represents the error message.       |

---

## Constructor

### `ErrorResponse(String code, String message)`
This constructor initializes an instance of the `ErrorResponse` class with the provided error code and message.

#### Parameters:
- `code`: The error code as a `String`.
- `message`: The error message as a `String`.

---

## Methods

| Method Name       | Return Type | Description                                      |
|-------------------|-------------|--------------------------------------------------|
| `getCode()`       | `String`    | Retrieves the value of the `code` attribute.    |
| `setCode(String code)` | `void` | Sets the value of the `code` attribute.         |
| `getMessage()`    | `String`    | Retrieves the value of the `message` attribute. |
| `setMessage(String message)` | `void` | Sets the value of the `message` attribute.    |

---

## Insights

- **Purpose**: The `ErrorResponse` class is primarily used as a data structure to represent error information. It does not contain any business logic or complex operations.
- **Usage**: This class can be utilized in APIs or applications to standardize error responses, making it easier to communicate issues to clients or users.
- **Extensibility**: While the class is simple, it can be extended to include additional attributes such as timestamps, error severity levels, or other metadata related to errors.
- **Encapsulation**: The class uses private attributes with public getter and setter methods, ensuring proper encapsulation and controlled access to its data.
