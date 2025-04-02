# supplierService.js: Supplier Management Service

## Overview
This module provides functionalities to interact with a supplier management API. It includes methods to retrieve all suppliers and create new suppliers, ensuring data validation and formatting before sending requests.

## Process Flow

```mermaid
flowchart TD
    Start("Start")
    GetAllSuppliers["getAllSuppliers()"]
    FetchSuppliers["Fetch suppliers from API"]
    CheckResponse{"Response OK?"}
    ParseJSON["Parse response as JSON"]
    ThrowError["Throw error: Failed to fetch suppliers"]
    End("End")
    
    CreateSupplier["createSupplier(supplier)"]
    CleanCNPJ["Remove non-numeric characters from CNPJ"]
    PrepareRequest["Prepare POST request"]
    SendRequest["Send request to API"]
    CheckPostResponse{"Response OK?"}
    ParsePostJSON["Parse response as JSON"]
    ThrowPostError["Throw error: Failed to create supplier"]

    Start --> GetAllSuppliers --> FetchSuppliers --> CheckResponse
    CheckResponse -->|Yes| ParseJSON --> End
    CheckResponse -->|No| ThrowError --> End

    Start --> CreateSupplier --> CleanCNPJ --> PrepareRequest --> SendRequest --> CheckPostResponse
    CheckPostResponse -->|Yes| ParsePostJSON --> End
    CheckPostResponse -->|No| ThrowPostError --> End
```


## Insights
- **Error Handling**: Both methods include error handling to ensure proper feedback when API requests fail.
- **Data Validation**: The `createSupplier` method sanitizes the `cnpj` field by removing non-numeric characters before sending the request.
- **Asynchronous Operations**: The module uses `async/await` for handling asynchronous API calls, ensuring better readability and maintainability.
- **API Interaction**: The module interacts with a REST API hosted at `http://localhost:8081`.

## Dependencies

```mermaid
flowchart LR
    supplierService --- |"Calls"| serverUrl
    supplierService --- |"Calls"| fetch
```


- `serverUrl`: Base URL for the API.
- `fetch`: Used for making HTTP requests to the API.

## External References
- `serverUrl`: Base URL for the supplier API (`http://localhost:8081`).
- `fetch`: Native JavaScript function for making HTTP requests.
