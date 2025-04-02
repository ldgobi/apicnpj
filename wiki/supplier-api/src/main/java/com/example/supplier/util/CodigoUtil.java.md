# Documentation: `CodigoUtil.java`

## Overview
The `CodigoUtil` class provides utility methods for validating Brazilian CNPJ numbers. A CNPJ (Cadastro Nacional da Pessoa Jurídica) is a unique identifier assigned to companies in Brazil. This class includes logic to verify the validity of a CNPJ based on its checksum calculation.

---

## Class: `CodigoUtil`

### Package
The class is part of the package:
```
com.example.supplier.util
```

### Purpose
The primary purpose of this class is to validate CNPJ numbers using the standard algorithm defined by Brazilian regulations.

---

## Method Details

### `isValidCNPJ(long cnpj)`
#### Description
This method validates a given CNPJ number by performing checksum calculations. It ensures the CNPJ adheres to the expected format and verifies its two check digits.

#### Parameters
| Name   | Type   | Description                          |
|--------|--------|--------------------------------------|
| `cnpj` | `long` | The CNPJ number to be validated.     |

#### Return Value
| Type      | Description                                      |
|-----------|--------------------------------------------------|
| `boolean` | Returns `true` if the CNPJ is valid, otherwise `false`. |

#### Logic
1. Converts the `long` CNPJ into a 14-character string, padding with zeros if necessary.
2. Validates the length of the CNPJ string (must be 14 characters).
3. Calculates the first check digit using the first 12 digits and a predefined weight array (`weight1`).
4. Calculates the second check digit using the first 13 digits and another predefined weight array (`weight2`).
5. Compares the calculated check digits with the last two digits of the CNPJ string.
6. Returns `false` if any exception occurs during processing.

#### Algorithm Details
- **Weight Arrays**:
  - `weight1`: `{5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}`
  - `weight2`: `{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}`
- **Checksum Calculation**:
  - Multiply each digit of the CNPJ by its corresponding weight.
  - Sum the results.
  - Compute the modulus (`mod`) of the sum by 11.
  - Determine the check digit:
    - If `mod < 2`, the check digit is `0`.
    - Otherwise, the check digit is `11 - mod`.

---

### `main(String[] args)`
#### Description
A simple demonstration of the `isValidCNPJ` method. It validates a hardcoded example CNPJ and prints the result to the console.

#### Parameters
| Name   | Type         | Description                          |
|--------|--------------|--------------------------------------|
| `args` | `String[]`   | Command-line arguments (not used).   |

#### Example Output
For the hardcoded CNPJ `12345678000195L`, the output will be:
```
CNPJ is valid: false
```

---

## Insights

### Strengths
- Implements the standard CNPJ validation algorithm, ensuring compliance with Brazilian regulations.
- Handles exceptions gracefully, returning `false` for invalid inputs or unexpected errors.

### Limitations
- The method assumes the input CNPJ is numeric and does not handle non-numeric inputs.
- The validation logic does not account for special cases like reserved or invalid CNPJs (e.g., `00000000000000`).

### Potential Enhancements
- Add support for validating CNPJs provided as `String` to handle cases with formatting (e.g., `12.345.678/0001-95`).
- Include additional checks for reserved or invalid CNPJs.
- Provide more detailed error handling and logging for debugging purposes.

---

## Metadata
| Key         | Value               |
|-------------|---------------------|
| File Name   | `CodigoUtil.java`   |
| Package     | `com.example.supplier.util` |
