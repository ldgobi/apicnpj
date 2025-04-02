# SupplierService Documentation

## Overview
The `SupplierService` class is a service layer implementation in a Spring-based application. It provides business logic for managing `Supplier` entities, including creation, retrieval, updating, and deletion. This class interacts with the `SupplierRepository` for database operations and uses utility methods for validation.

---

## Class Details

### **Package**
`com.example.supplier.service`

### **Annotations**
- `@Service`: Marks the class as a Spring service component, enabling it to be automatically detected and managed by the Spring container.

### **Dependencies**
- **SupplierRepository**: Handles database operations for `Supplier` entities.
- **CodigoUtil**: Provides utility methods for validation, specifically for checking the validity of CNPJ (Brazilian company registration number).

---

## Methods

### **1. createSupplier(Supplier supplier)**
Creates a new `Supplier` entity after validating its CNPJ.

#### Parameters:
- `supplier`: The `Supplier` object to be created.

#### Returns:
- The saved `Supplier` object.

#### Exceptions:
- Throws `IllegalArgumentException` if the CNPJ is invalid.

---

### **2. getAllSuppliers()**
Retrieves all `Supplier` entities from the database.

#### Returns:
- A `List<Supplier>` containing all suppliers.

---

### **3. getSupplierById(Long id)**
Fetches a specific `Supplier` entity by its ID.

#### Parameters:
- `id`: The ID of the supplier to retrieve.

#### Returns:
- An `Optional<Supplier>` containing the supplier if found, or empty if not.

---

### **4. updateSupplier(Long id, Supplier supplierDetails)**
Updates an existing `Supplier` entity with new details.

#### Parameters:
- `id`: The ID of the supplier to update.
- `supplierDetails`: A `Supplier` object containing updated information.

#### Returns:
- The updated `Supplier` object.

#### Exceptions:
- Throws `IllegalArgumentException` if the CNPJ is invalid.
- Throws `RuntimeException` if the supplier with the given ID is not found.

---

### **5. deleteSupplier(Long id)**
Deletes a `Supplier` entity by its ID.

#### Parameters:
- `id`: The ID of the supplier to delete.

#### Returns:
- `true` if the deletion is successful.

#### Exceptions:
- Throws `RuntimeException` if the supplier with the given ID is not found.

---

## Insights

### **Validation**
The class uses `CodigoUtil.isValidCNPJ()` to ensure that the CNPJ provided for a supplier is valid. This validation is applied during both creation and update operations.

### **Error Handling**
- The service throws specific exceptions (`IllegalArgumentException` and `RuntimeException`) to handle invalid input and missing entities, respectively.
- These exceptions should be properly handled in the controller layer to provide meaningful feedback to the user.

### **CRUD Operations**
The class implements all basic CRUD operations for the `Supplier` entity:
- **Create**: `createSupplier`
- **Read**: `getAllSuppliers`, `getSupplierById`
- **Update**: `updateSupplier`
- **Delete**: `deleteSupplier`

### **Spring Integration**
The use of `@Autowired` ensures dependency injection for the `SupplierRepository`, simplifying the management of database operations.

### **Potential Enhancements**
- Add pagination and sorting to `getAllSuppliers()` for better scalability.
- Implement soft deletion instead of hard deletion in `deleteSupplier()` to preserve historical data.
- Enhance exception handling with custom exception classes for better clarity.

---

## Dependencies Table

| Dependency          | Purpose                                                                 |
|----------------------|-------------------------------------------------------------------------|
| `SupplierRepository` | Handles database operations for `Supplier` entities.                  |
| `CodigoUtil`         | Provides utility methods for validating CNPJ.                         |
| `Spring Framework`   | Enables dependency injection and service management via annotations.  |
