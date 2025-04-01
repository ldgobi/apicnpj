# SupplierController Documentation

## Overview

The `SupplierController` is a RESTful controller in a Spring Boot application that manages CRUD (Create, Read, Update, Delete) operations for `Supplier` entities. It acts as an intermediary between the client and the `SupplierService`, handling HTTP requests and returning appropriate responses.

## Class Details

### Package
`com.example.supplier.controller`

### Annotations
- `@RestController`: Indicates that this class is a REST controller where each method returns a domain object instead of a view.
- `@RequestMapping("/api/suppliers")`: Maps all HTTP requests starting with `/api/suppliers` to this controller.

### Dependencies
- **`SupplierService`**: The service layer responsible for business logic related to `Supplier` entities. It is injected using the `@Autowired` annotation.

## Endpoints

| HTTP Method | Endpoint            | Description                                                                 | Request Body         | Response Body       | Status Codes                     |
|-------------|---------------------|-----------------------------------------------------------------------------|----------------------|---------------------|-----------------------------------|
| `GET`       | `/api/suppliers`    | Retrieves a list of all suppliers.                                          | None                 | `List<Supplier>`    | `200 OK`                         |
| `GET`       | `/api/suppliers/{id}` | Retrieves a specific supplier by its ID.                                    | None                 | `Supplier`          | `200 OK`, `404 Not Found`        |
| `POST`      | `/api/suppliers`    | Creates a new supplier.                                                     | `Supplier`           | `Supplier`          | `201 Created`                    |
| `PUT`       | `/api/suppliers/{id}` | Updates an existing supplier by its ID.                                     | `Supplier`           | `Supplier`          | `200 OK`, `404 Not Found`        |
| `DELETE`    | `/api/suppliers/{id}` | Deletes a supplier by its ID.                                               | None                 | None                | `204 No Content`, `404 Not Found`|

## Methods

### `getAllSuppliers()`
- **Description**: Fetches all suppliers from the database.
- **Return Type**: `List<Supplier>`
- **HTTP Method**: `GET`
- **Endpoint**: `/api/suppliers`

### `getSupplierById(Long id)`
- **Description**: Fetches a supplier by its unique ID.
- **Parameters**:
  - `@PathVariable Long id`: The ID of the supplier to retrieve.
- **Return Type**: `ResponseEntity<Supplier>`
- **HTTP Method**: `GET`
- **Endpoint**: `/api/suppliers/{id}`
- **Behavior**:
  - Returns `200 OK` with the supplier if found.
  - Returns `404 Not Found` if the supplier does not exist.

### `createSupplier(Supplier supplier)`
- **Description**: Creates a new supplier.
- **Parameters**:
  - `@RequestBody Supplier supplier`: The supplier object to be created.
- **Return Type**: `Supplier`
- **HTTP Method**: `POST`
- **Endpoint**: `/api/suppliers`

### `updateSupplier(Long id, Supplier supplierDetails)`
- **Description**: Updates an existing supplier's details.
- **Parameters**:
  - `@PathVariable Long id`: The ID of the supplier to update.
  - `@RequestBody Supplier supplierDetails`: The updated supplier details.
- **Return Type**: `ResponseEntity<Supplier>`
- **HTTP Method**: `PUT`
- **Endpoint**: `/api/suppliers/{id}`
- **Behavior**:
  - Returns `200 OK` with the updated supplier if successful.
  - Returns `404 Not Found` if the supplier does not exist.

### `deleteSupplier(Long id)`
- **Description**: Deletes a supplier by its ID.
- **Parameters**:
  - `@PathVariable Long id`: The ID of the supplier to delete.
- **Return Type**: `ResponseEntity<Void>`
- **HTTP Method**: `DELETE`
- **Endpoint**: `/api/suppliers/{id}`
- **Behavior**:
  - Returns `204 No Content` if the deletion is successful.
  - Returns `404 Not Found` if the supplier does not exist.

## Insights

- **Error Handling**: The controller uses `ResponseEntity` to handle cases where a supplier is not found (`404 Not Found`) or when a deletion is successful but has no content to return (`204 No Content`).
- **Dependency Injection**: The `SupplierService` is injected using Spring's `@Autowired` annotation, promoting loose coupling and testability.
- **RESTful Design**: The controller adheres to RESTful principles, with clear separation of HTTP methods for different operations (e.g., `GET` for retrieval, `POST` for creation, `PUT` for updates, and `DELETE` for deletion).
- **Scalability**: The use of a service layer (`SupplierService`) allows for easy extension of business logic without modifying the controller directly.
