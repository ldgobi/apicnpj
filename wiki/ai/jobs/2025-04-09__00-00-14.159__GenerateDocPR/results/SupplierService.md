# SupplierService.java: Supplier Management Service

## Overview
The `SupplierService` class is a service layer responsible for managing supplier-related operations. It provides methods to create, retrieve, update, and delete supplier records while ensuring data validation and interaction with the underlying repository.

## Process Flow
```mermaid
flowchart TD
    A("createSupplier(supplier)") --> B{"isValidCNPJ?"}
    B -- "No" --> C["Throw IllegalArgumentException"]
    B -- "Yes" --> D["Save Supplier to Repository"]

    E("getAllSuppliers()") --> F["Retrieve All Suppliers from Repository"]

    G("getSupplierById(id)") --> H["Find Supplier by ID in Repository"]

    I("updateSupplier(id, supplierDetails)") --> J{"isValidCNPJ?"}
    J -- "No" --> K["Throw IllegalArgumentException"]
    J -- "Yes" --> L{"Supplier Exists?"}
    L -- "No" --> M["Throw RuntimeException"]
    L -- "Yes" --> N["Update Supplier Details"]
    N --> O["Save Updated Supplier to Repository"]

    P("deleteSupplier(id)") --> Q{"Supplier Exists?"}
    Q -- "No" --> R["Throw RuntimeException"]
    Q -- "Yes" --> S["Delete Supplier by ID"]
```

## Insights
- **Validation**: The `createSupplier` and `updateSupplier` methods validate the supplier's CNPJ using the `CodigoUtil.isValidCNPJ` utility method.
- **Error Handling**: Throws exceptions (`IllegalArgumentException` or `RuntimeException`) for invalid data or when a supplier is not found.
- **CRUD Operations**:
  - **Create**: Adds a new supplier to the repository.
  - **Read**: Retrieves all suppliers or a specific supplier by ID.
  - **Update**: Updates supplier details if the supplier exists and the CNPJ is valid.
  - **Delete**: Deletes a supplier by ID if it exists.
- **Repository Interaction**: Relies on `SupplierRepository` for database operations.

## Dependencies
```mermaid
flowchart LR
    SupplierService --- |"Uses"| SupplierRepository
    SupplierService --- |"Uses"| Supplier
    SupplierService --- |"Uses"| CodigoUtil
```

- `SupplierRepository`: Handles database operations for the `Supplier` entity.
- `Supplier`: Represents the supplier entity with attributes such as `nome`, `cnpj`, `nomeContato`, `emailContato`, and `telefoneContato`.
- `CodigoUtil`: Provides utility methods, including `isValidCNPJ`, for validating supplier data.

## Data Manipulation (SQL)
The `SupplierRepository` is assumed to handle the following operations:
- **INSERT**: Adds a new supplier to the database.
- **SELECT**: Retrieves supplier records (all or by ID).
- **UPDATE**: Updates existing supplier records.
- **DELETE**: Removes a supplier record by ID.
