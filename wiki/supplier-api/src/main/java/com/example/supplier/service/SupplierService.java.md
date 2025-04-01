# SupplierService Documentation

## Overview
The `SupplierService` class is a service layer in a Spring-based application that manages operations related to suppliers. It interacts with the `SupplierRepository` to perform CRUD (Create, Read, Update, Delete) operations and includes validation logic for supplier data, such as verifying the validity of a CNPJ (Brazilian company registration number).

---

## Class Details

### Package
`com.example.supplier.service`

### Dependencies
The class relies on the following components:
- **`SupplierRepository`**: Handles database operations for the `Supplier` entity.
- **`CodigoUtil`**: Provides utility methods, such as validating CNPJ values.
- **Spring Framework**:
  - `@Service`: Marks the class as a service component in the Spring context.
  - `@Autowired`: Injects dependencies automatically.

---

## Methods

### `createSupplier(Supplier supplier)`
Creates a new supplier in the database after validating the CNPJ.

#### Parameters:
- `supplier`: An instance of the `Supplier` entity containing supplier details.

#### Returns:
- The saved `Supplier` object.

#### Exceptions:
- Throws `IllegalArgumentException` if the CNPJ is invalid.

---

### `getAllSuppliers()`
Retrieves all suppliers from the database.

#### Returns:
- A `List<Supplier>` containing all suppliers.

---

### `getSupplierById(Long id)`
Fetches a supplier by its unique identifier.

#### Parameters:
- `id`: The ID of the supplier to retrieve.

#### Returns:
- An `Optional<Supplier>` containing the supplier if found, or empty if not.

---

### `updateSupplier(Long id, Supplier supplierDetails)`
Updates an existing supplier's details after validating the CNPJ.

#### Parameters:
- `id`: The ID of the supplier to update.
- `supplierDetails`: An instance of `Supplier` containing updated details.

#### Returns:
- The updated `Supplier` object.

#### Exceptions:
- Throws `IllegalArgumentException` if the CNPJ is invalid.
- Throws `RuntimeException` if the supplier with the given ID is not found.

---

### `deleteSupplier(Long id)`
Deletes a supplier by its unique identifier.

#### Parameters:
- `id`: The ID of the supplier to delete.

#### Returns:
- `true` if the supplier was successfully deleted.

#### Exceptions:
- Throws `RuntimeException` if the supplier with the given ID is not found.

---

## Insights

### Validation Logic
The class uses `CodigoUtil.isValidCNPJ()` to ensure that the CNPJ provided for a supplier is valid. This adds an extra layer of data integrity.

### Exception Handling
The service throws specific exceptions (`IllegalArgumentException` and `RuntimeException`) to handle invalid input and missing entities, ensuring robust error handling.

### Dependency Injection
The use of `@Autowired` simplifies dependency management, making the class easier to test and maintain.

### CRUD Operations
The class provides full CRUD functionality for the `Supplier` entity:
- **Create**: `createSupplier`
- **Read**: `getAllSuppliers`, `getSupplierById`
- **Update**: `updateSupplier`
- **Delete**: `deleteSupplier`

### Potential Enhancements
- Add logging for better traceability of operations.
- Implement pagination for `getAllSuppliers` to handle large datasets.
- Extend validation logic to include other fields like email and phone number.

---

## Related Components

### `Supplier`
The entity representing a supplier. It includes fields such as `nome`, `cnpj`, `nomeContato`, `emailContato`, and `telefoneContato`.

### `SupplierRepository`
A Spring Data repository interface for database operations on the `Supplier` entity.

### `CodigoUtil`
A utility class for validating CNPJ values.

---

## Metadata

| **File Name** | `SupplierService.java` |
|---------------|-------------------------|
