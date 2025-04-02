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
- **`SupplierService`**: The service layer responsible for business logic related to `Supplier` entities. It is injected using `@Autowired`.

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
- **Returns**: A list of `Supplier` objects.

### `getSupplierById(Long id)`
- **Description**: Fetches a supplier by its unique ID.
- **Parameters**: 
  - `id`: The ID of the supplier to retrieve.
- **Returns**: 
  - `200 OK` with the `Supplier` object if found.
  - `404 Not Found` if the supplier does not exist.

### `createSupplier(Supplier supplier)`
- **Description**: Creates a new supplier in the database.
- **Parameters**: 
  - `supplier`: The `Supplier` object to be created.
- **Returns**: The created `Supplier` object.

### `updateSupplier(Long id, Supplier supplierDetails)`
- **Description**: Updates an existing supplier's details.
- **Parameters**: 
  - `id`: The ID of the supplier to update.
  - `supplierDetails`: The updated `Supplier` object.
- **Returns**: 
  - `200 OK` with the updated `Supplier` object if the update is successful.
  - `404 Not Found` if the supplier does not exist.

### `deleteSupplier(Long id)`
- **Description**: Deletes a supplier by its ID.
- **Parameters**: 
  - `id`: The ID of the supplier to delete.
- **Returns**: 
  - `204 No Content` if the deletion is successful.
  - `404 Not Found` if the supplier does not exist.

## Insights

1. **Error Handling**: The controller uses `ResponseEntity` to handle cases where a supplier is not found (`404 Not Found`) or when a deletion is successful (`204 No Content`).
2. **RESTful Design**: The controller adheres to RESTful principles, with clear separation of HTTP methods for different operations (GET for retrieval, POST for creation, PUT for updates, DELETE for deletion).
3. **Dependency Injection**: The `SupplierService` is injected using Spring's `@Autowired` annotation, promoting loose coupling and testability.
4. **Path Variables**: The use of `@PathVariable` ensures that dynamic parts of the URL (e.g., supplier ID) are mapped to method parameters.
5. **Request Body Mapping**: The `@RequestBody` annotation is used to map incoming JSON payloads to `Supplier` objects for POST and PUT operations.
