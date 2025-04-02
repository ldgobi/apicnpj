package com.example.supplier.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CodigoUtilTest {

    // Placeholder for Batch 1 tests
    //         @Test
        public void isValidCNPJ_ShouldReturnTrue_ForValidCNPJ() {
            // Valid CNPJ with correct check digits
            long validCNPJ = 12345678000195L;
            assertTrue(CodigoUtil.isValidCNPJ(validCNPJ), "Expected valid CNPJ to return true");
        }
    
        @Test
        public void isValidCNPJ_ShouldReturnFalse_ForInvalidCNPJ() {
            // Invalid CNPJ with incorrect check digits
            long invalidCNPJ = 12345678000194L;
            assertFalse(CodigoUtil.isValidCNPJ(invalidCNPJ), "Expected invalid CNPJ to return false");
        }
    
        @Test
        public void isValidCNPJ_ShouldReturnFalse_ForShortCNPJ() {
            // CNPJ shorter than 14 digits
            long shortCNPJ = 12345678L;
            assertFalse(CodigoUtil.isValidCNPJ(shortCNPJ), "Expected short CNPJ to return false");
        }
    
        @Test
        public void isValidCNPJ_ShouldReturnFalse_ForLongCNPJ() {
            // CNPJ longer than 14 digits
            long longCNPJ = 123456789012345L;
            assertFalse(CodigoUtil.isValidCNPJ(longCNPJ), "Expected long CNPJ to return false");
        }
    
        @Test
        public void isValidCNPJ_ShouldReturnFalse_ForNonNumericCNPJ() {
            // Non-numeric CNPJ (simulated by passing a negative number)
            long nonNumericCNPJ = -12345678000195L;
            assertFalse(CodigoUtil.isValidCNPJ(nonNumericCNPJ), "Expected non-numeric CNPJ to return false");
        }
    
        @Test
        public void main_ShouldPrintValidMessage_ForValidCNPJ() {
            // Redirecting System.out to capture the output
            java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
            System.setOut(new java.io.PrintStream(outContent));
    
            // Running the main method
            CodigoUtil.main(new String[]{});
    
            // Restoring System.out
            System.setOut(System.out);
    
            // Asserting the output
            String expectedOutput = "CNPJ is valid: true" + System.lineSeparator();
            assertEquals(expectedOutput, outContent.toString(), "Expected main method to print valid CNPJ message");
        }
}
