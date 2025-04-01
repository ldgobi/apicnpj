# Documentation: `CodigoUtil.java`

## Overview
The `CodigoUtil` class provides utility methods for validating Brazilian CNPJ numbers. A CNPJ (Cadastro Nacional da Pessoa Jurídica) is a unique identifier for companies registered in Brazil. This class includes logic to verify the validity of a given CNPJ based on its checksum calculation.

---

## Class: `CodigoUtil`

### Package
`com.example.supplier.util`

### Purpose
The class is designed to validate CNPJ numbers using the official algorithm for checksum verification.

---

## Method Details

### `isValidCNPJ(long cnpj)`
#### Description
Validates a given CNPJ number by checking its format and performing checksum calculations.

#### Parameters
| Name  | Type   | Description                                      |
|-------|--------|--------------------------------------------------|
| `cnpj`| `long` | The CNPJ number to be validated.                 |

#### Return Value
| Type      | Description                                      |
|-----------|--------------------------------------------------|
| `boolean` | Returns `true` if the CNPJ is valid, otherwise `false`. |

#### Logic
1. Converts the `long` CNPJ number into a 14-character string.
2. Checks if the string length is exactly 14 characters.
3. Performs two checksum calculations:
   - **First Checksum**: Uses the first 12 digits and a predefined weight array (`weight1`).
   - **Second Checksum**: Uses the first 13 digits and another predefined weight array (`weight2`).
4. Validates the last two digits of the CNPJ against the calculated checksum digits.
5. Returns `false` if any exception occurs during processing.

#### Algorithm Details
- **Weights for Checksum**:
  - `weight1`: `{5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}`
  - `weight2`: `{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}`
- **Checksum Calculation**:
  - Multiply each digit of the CNPJ by its corresponding weight.
  - Sum the results.
  - Compute the modulus (`mod`) of the sum by 11.
  - Determine the checksum digit:
    - If `mod < 2`, the checksum digit is `0`.
    - Otherwise, the checksum digit is `11 - mod`.

#### Example Usage
```java
long cnpj = 12345678000195L; // Example CNPJ
System.out.println("CNPJ is valid: " + CodigoUtil.isValidCNPJ(cnpj));
```

---

### `main(String[] args)`
#### Description
A simple entry point for testing the `isValidCNPJ` method.

#### Parameters
| Name   | Type         | Description                          |
|--------|--------------|--------------------------------------|
| `args` | `String[]`   | Command-line arguments (not used).  |

#### Functionality
- Tests the `isValidCNPJ` method with a sample CNPJ (`12345678000195L`).
- Prints the validation result to the console.

---

## Insights

### Strengths
- Implements the official CNPJ validation algorithm, ensuring compliance with Brazilian standards.
- Handles exceptions gracefully, returning `false` for invalid inputs or errors during processing.

### Limitations
- The method assumes the input CNPJ is a valid `long` number. It does not handle cases where the CNPJ is provided in other formats (e.g., as a string with special characters like dots or dashes).
- The validation logic does not provide detailed error messages for invalid inputs.

### Potential Enhancements
- Add support for CNPJ input as a formatted string (e.g., `"12.345.678/0001-95"`).
- Provide detailed error messages or logging for invalid inputs.
- Extend the utility class to include other Brazilian document validations (e.g., CPF).

---

## Metadata
| Key         | Value                |
|-------------|----------------------|
| File Name   | `CodigoUtil.java`    |
| Package     | `com.example.supplier.util` |
