# Documentation: `CodigoUtil.java`

## Overview
The `CodigoUtil` class provides utility methods for validating Brazilian CNPJ numbers. A CNPJ (Cadastro Nacional da Pessoa Jurídica) is a unique identifier for companies registered in Brazil. This class includes logic to verify the validity of a CNPJ based on its checksum calculation.

---

## Class: `CodigoUtil`

### Package
`com.example.supplier.util`

### Purpose
The class is designed to validate CNPJ numbers using the standard algorithm defined by Brazilian regulations. It includes a method to perform the validation and a `main` method for testing purposes.

---

## Methods

### `isValidCNPJ(long cnpj)`
#### Description
Validates a given CNPJ number by checking its format and calculating its checksum digits.

#### Parameters
| Name  | Type   | Description                          |
|-------|--------|--------------------------------------|
| `cnpj` | `long` | The CNPJ number to be validated.    |

#### Return Value
| Type      | Description                              |
|-----------|------------------------------------------|
| `boolean` | Returns `true` if the CNPJ is valid, otherwise `false`. |

#### Logic
1. Converts the `long` CNPJ number into a 14-character string.
2. Checks if the length of the string is exactly 14 characters.
3. Calculates the first checksum digit using a predefined weight array (`weight1`).
4. Calculates the second checksum digit using another predefined weight array (`weight2`).
5. Compares the calculated checksum digits with the last two digits of the CNPJ string.
6. Returns `true` if both checksum digits match; otherwise, returns `false`.

#### Exception Handling
If any error occurs during the validation process (e.g., invalid input), the method catches the exception and returns `false`.

---

### `main(String[] args)`
#### Description
A test method to demonstrate the usage of the `isValidCNPJ` method.

#### Parameters
| Name  | Type         | Description                          |
|-------|--------------|--------------------------------------|
| `args` | `String[]`   | Command-line arguments (not used).  |

#### Functionality
1. Defines a sample CNPJ number (`12345678000195L`).
2. Calls the `isValidCNPJ` method to validate the sample CNPJ.
3. Prints the validation result to the console.

---

## Insights

### CNPJ Validation Algorithm
The validation algorithm uses two weight arrays (`weight1` and `weight2`) to calculate checksum digits:
- **Weight Array 1 (`weight1`)**: Used for the first checksum digit calculation.
- **Weight Array 2 (`weight2`)**: Used for the second checksum digit calculation.

The checksum digits are calculated as follows:
1. Multiply each digit of the CNPJ (excluding the checksum digits) by its corresponding weight.
2. Sum the results.
3. Compute the modulus (`mod`) of the sum by 11.
4. Determine the checksum digit:
   - If `mod < 2`, the checksum digit is `0`.
   - Otherwise, the checksum digit is `11 - mod`.

### Error Handling
The method gracefully handles errors (e.g., invalid input or unexpected exceptions) by returning `false`.

### Example Usage
The `main` method provides an example of how to use the `isValidCNPJ` method. It validates a sample CNPJ and prints the result.

### Limitations
- The method assumes the input CNPJ is a numeric value (`long`). Non-numeric inputs are not supported.
- The validation does not check for other business rules related to CNPJ, such as reserved or invalid sequences.

---

## Example

### Input
```java
long cnpj = 12345678000195L;
```

### Output
```
CNPJ is valid: false
```

---

## Dependencies
This class does not rely on external libraries or frameworks. It uses standard Java functionality (`String`, `Character`, etc.).

---

## File Metadata
| Key         | Value                |
|-------------|----------------------|
| **File Name** | `CodigoUtil.java`   |
| **Package**   | `com.example.supplier.util` |
