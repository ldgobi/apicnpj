# SupplierForm Component Documentation

## Overview
The `SupplierForm` component is a React-based form designed to create supplier records. It includes input fields for supplier details such as name, CNPJ (Brazilian company registration number), contact name, contact email, and contact phone number. The form validates the CNPJ format and ensures all fields are filled before submission. Upon successful validation, the supplier data is sent to a backend service for storage.

---

## Features
- **State Management**: Utilizes React's `useState` hook to manage form data and error messages.
- **Input Masking**: Implements `react-input-mask` for formatting the CNPJ input field.
- **Validation**: Includes custom logic to validate the CNPJ format.
- **Error Handling**: Displays error messages for invalid inputs or failed submissions.
- **Integration**: Sends supplier data to a backend service using the `createSupplier` function.

---

## Code Structure

### Data Structure
The component maintains the following state:
| State Variable | Description |
|----------------|-------------|
| `supplier`     | Object containing supplier details (`nome`, `cnpj`, `nomeContato`, `emailContato`, `telefoneContato`). |
| `error`        | String to store error messages for validation or submission failures. |

### Logic
#### Functions
| Function Name   | Description |
|-----------------|-------------|
| `handleChange`  | Updates the `supplier` state when an input field changes. |
| `validateCNPJ`  | Validates the CNPJ format using custom logic. Ensures the CNPJ has 14 digits and checks its verification digits. |
| `handleSubmit`  | Handles form submission. Validates inputs, checks CNPJ format, and sends data to the backend service. Displays error messages for invalid inputs or submission failures. |

#### Validation Logic
The `validateCNPJ` function:
1. Removes non-numeric characters from the CNPJ.
2. Ensures the CNPJ has exactly 14 digits.
3. Validates the two verification digits using a mathematical algorithm.

---

## Dependencies
| Dependency         | Purpose |
|--------------------|---------|
| `react`            | Provides core React functionality for state management and rendering. |
| `react-input-mask` | Masks the CNPJ input field to enforce a specific format. |
| `supplierService`  | Contains the `createSupplier` function for backend integration. |

---

## Insights
- **CNPJ Validation**: The validation logic is specific to Brazilian company registration numbers. It ensures compliance with local standards, making the component suitable for applications targeting Brazilian businesses.
- **Error Handling**: The component provides user-friendly error messages, improving the user experience during form submission.
- **Reusability**: The modular design of the `SupplierForm` component allows it to be reused in different parts of the application where supplier creation is required.
- **Input Masking**: The use of `react-input-mask` simplifies the enforcement of input formats, reducing the likelihood of user errors.

---

## Usage
To use the `SupplierForm` component:
1. Import the component:
   ```javascript
   import SupplierForm from './SupplierForm';
   ```
2. Render it in your application:
   ```javascript
   <SupplierForm />
   ```

Ensure the `createSupplier` function in `supplierService` is properly implemented to handle supplier data submission.
