# SupplierController Documentation

## Overview
The `SupplierController` is a RESTful controller in a Spring Boot application that manages CRUD operations for `Supplier` entities. It provides endpoints for creating, retrieving, updating, and deleting suppliers. The controller interacts with the `SupplierService` to perform business logic and data manipulation.

## Class Details

### Package
```java
com.example.supplier.controller
```

### Dependencies
- **Spring Framework**:
  - `@RestController`: Marks the class as a REST controller.
  - `@RequestMapping`: Maps HTTP requests to specific handler methods.
  - `@Autowired`: Injects the `SupplierService` dependency.
  - `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`: Maps HTTP methods to specific endpoints.
  - `ResponseEntity`: Represents the HTTP response, including status and body.
- **Java Standard Library**:
  - `Optional`: Handles optional values for supplier retrieval.

### Associated Classes
- `Supplier`: Represents the supplier entity.
- `SupplierService`: Provides business logic and data access for supplier operations.

---

## Endpoints

### 1. **Get All Suppliers**
- **URL**: `/api/suppliers`
- **HTTP Method**: `GET`
- **Description**: Retrieves a list of all suppliers.
- **Return Type**: `List<Supplier>`
- **Response**: 
  - `200 OK`: Returns the list of suppliers.

---

### 2. **Get Supplier by ID**
- **URL**: `/api/suppliers/{id}`
- **HTTP Method**: `GET`
- **Description**: Retrieves a supplier by its unique ID.
- **Parameters**:
  - `id` (Path Variable): The ID of the supplier to retrieve.
- **Return Type**: `ResponseEntity<Supplier>`
- **Response**:
  - `200 OK`: Returns the supplier if found.
  - `404 Not Found`: If the supplier does not exist.

---

### 3. **Create Supplier**
- **URL**: `/api/suppliers`
- **HTTP Method**: `POST`
- **Description**: Creates a new supplier.
- **Request Body**: `Supplier` object containing supplier details.
- **Return Type**: `Supplier`
- **Response**:
  - `201 Created`: Returns the created supplier.

---

### 4. **Update Supplier**
- **URL**: `/api/suppliers/{id}`
- **HTTP Method**: `PUT`
- **Description**: Updates an existing supplier.
- **Parameters**:
  - `id` (Path Variable): The ID of the supplier to update.
  - `supplierDetails` (Request Body): `Supplier` object containing updated details.
- **Return Type**: `ResponseEntity<Supplier>`
- **Response**:
  - `200 OK`: Returns the updated supplier.
  - `404 Not Found`: If the supplier does not exist.

---

### 5. **Delete Supplier**
- **URL**: `/api/suppliers/{id}`
- **HTTP Method**: `DELETE`
- **Description**: Deletes a supplier by its ID.
- **Parameters**:
  - `id` (Path Variable): The ID of the supplier to delete.
- **Return Type**: `ResponseEntity<Void>`
- **Response**:
  - `204 No Content`: If the supplier is successfully deleted.
  - `404 Not Found`: If the supplier does not exist.

---

## Insights

### Design Patterns
- **Controller-Service Pattern**: The controller delegates business logic to the `SupplierService`, adhering to the separation of concerns principle.
- **RESTful API Design**: The controller follows REST conventions, using HTTP methods (`GET`, `POST`, `PUT`, `DELETE`) to represent CRUD operations.

### Error Handling
- The use of `ResponseEntity` ensures proper HTTP status codes are returned for different scenarios (e.g., `404 Not Found` for missing resources).

### Dependency Injection
- The `@Autowired` annotation is used to inject the `SupplierService` dependency, promoting loose coupling and testability.

### Optional Usage
- The `Optional` class is used to handle cases where a supplier may not exist, avoiding `NullPointerException` and improving code readability.

### Scalability
- The controller is designed to handle basic CRUD operations. Additional features like pagination, filtering, or sorting can be added to enhance functionality.

---

## File Metadata
- **File Name**: `SupplierController.java`
- **Location**: `com.example.supplier.controller`
