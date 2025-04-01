# SupplierService Documentation

## Overview
The `SupplierService` class is a service layer in a Spring-based application that manages operations related to `Supplier` entities. It interacts with the `SupplierRepository` to perform CRUD (Create, Read, Update, Delete) operations and includes validation logic for supplier data, such as verifying the validity of a CNPJ (Brazilian company registration number).

---

## Class Details

### **Package**
`com.example.supplier.service`

### **Annotations**
- `@Service`: Indicates that this class is a Spring service component, making it eligible for Spring's component scanning and dependency injection.

---

## Dependencies

### **Injected Dependencies**
| Dependency              | Description                                                                 |
|--------------------------|-----------------------------------------------------------------------------|
| `SupplierRepository`     | Repository interface for database operations on `Supplier` entities.       |
| `CodigoUtil`             | Utility class for validating CNPJ values.                                  |

---

## Methods

### **1. createSupplier(Supplier supplier)**

#### **Description**
Creates a new `Supplier` entity after validating its CNPJ.

#### **Parameters**
| Name       | Type       | Description                          |
|------------|------------|--------------------------------------|
| `supplier` | `Supplier` | The supplier entity to be created.  |

#### **Returns**
| Type       | Description                          |
|------------|--------------------------------------|
| `Supplier` | The saved supplier entity.          |

#### **Exceptions**
| Type                        | Description                          |
|-----------------------------|--------------------------------------|
| `IllegalArgumentException`  | Thrown if the CNPJ is invalid.      |

---

### **2. getAllSuppliers()**

#### **Description**
Retrieves all `Supplier` entities from the database.

#### **Parameters**
None.

#### **Returns**
| Type            | Description                          |
|------------------|--------------------------------------|
| `List<Supplier>` | List of all suppliers.              |

---

### **3. getSupplierById(Long id)**

#### **Description**
Fetches a `Supplier` entity by its ID.

#### **Parameters**
| Name | Type   | Description                          |
|------|--------|--------------------------------------|
| `id` | `Long` | The ID of the supplier to retrieve. |

#### **Returns**
| Type                  | Description                          |
|-----------------------|--------------------------------------|
| `Optional<Supplier>`  | The supplier entity wrapped in an `Optional`. |

---

### **4. updateSupplier(Long id, Supplier supplierDetails)**

#### **Description**
Updates an existing `Supplier` entity with new details after validating the CNPJ.

#### **Parameters**
| Name              | Type       | Description                          |
|-------------------|------------|--------------------------------------|
| `id`              | `Long`     | The ID of the supplier to update.   |
| `supplierDetails` | `Supplier` | The updated supplier details.       |

#### **Returns**
| Type       | Description                          |
|------------|--------------------------------------|
| `Supplier` | The updated supplier entity.        |

#### **Exceptions**
| Type                        | Description                          |
|-----------------------------|--------------------------------------|
| `IllegalArgumentException`  | Thrown if the CNPJ is invalid.      |
| `RuntimeException`          | Thrown if the supplier is not found.|

---

### **5. deleteSupplier(Long id)**

#### **Description**
Deletes a `Supplier` entity by its ID.

#### **Parameters**
| Name | Type   | Description                          |
|------|--------|--------------------------------------|
| `id` | `Long` | The ID of the supplier to delete.   |

#### **Returns**
| Type      | Description                          |
|-----------|--------------------------------------|
| `boolean` | Returns `true` if the deletion is successful. |

#### **Exceptions**
| Type               | Description                          |
|--------------------|--------------------------------------|
| `RuntimeException` | Thrown if the supplier is not found.|

---

## Insights

### **Validation**
- The `CodigoUtil.isValidCNPJ()` method is used to validate the CNPJ before creating or updating a supplier. This ensures data integrity and compliance with Brazilian business regulations.

### **Error Handling**
- The service uses exceptions (`IllegalArgumentException` and `RuntimeException`) to handle invalid input and missing entities, providing meaningful error messages.

### **CRUD Operations**
- The class provides full CRUD functionality for `Supplier` entities:
  - **Create**: `createSupplier()`
  - **Read**: `getAllSuppliers()` and `getSupplierById()`
  - **Update**: `updateSupplier()`
  - **Delete**: `deleteSupplier()`

### **Optional Usage**
- The `getSupplierById()` method returns an `Optional<Supplier>` to handle cases where the supplier may not exist, promoting safer null handling.

### **Spring Integration**
- The use of `@Autowired` ensures seamless dependency injection for the repository, adhering to Spring's IoC (Inversion of Control) principles.

### **Transactional Behavior**
- Although not explicitly annotated, the repository methods (`save`, `findAll`, `findById`, `deleteById`) are transactional by default in Spring Data JPA, ensuring consistency during database operations.
