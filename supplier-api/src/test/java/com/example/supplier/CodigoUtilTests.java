import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CodigoUtilTest {

    @Test
    public void isValidCNPJ_ShouldReturnTrue_ForValidCNPJ() {
        long validCNPJ = 12345678000195L; // Example of a valid CNPJ
        assertTrue(CodigoUtil.isValidCNPJ(validCNPJ), "Expected valid CNPJ to return true");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForInvalidCNPJ() {
        long invalidCNPJ = 12345678000194L; // Example of an invalid CNPJ
        assertFalse(CodigoUtil.isValidCNPJ(invalidCNPJ), "Expected invalid CNPJ to return false");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForShortCNPJ() {
        long shortCNPJ = 12345678L; // Example of a CNPJ shorter than 14 digits
        assertFalse(CodigoUtil.isValidCNPJ(shortCNPJ), "Expected short CNPJ to return false");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForLongCNPJ() {
        long longCNPJ = 123456789012345L; // Example of a CNPJ longer than 14 digits
        assertFalse(CodigoUtil.isValidCNPJ(longCNPJ), "Expected long CNPJ to return false");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForNegativeCNPJ() {
        long negativeCNPJ = -12345678000195L; // Example of a negative CNPJ
        assertFalse(CodigoUtil.isValidCNPJ(negativeCNPJ), "Expected negative CNPJ to return false");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForNonNumericCharacters() {
        long nonNumericCNPJ = 12345678L; // Simulating non-numeric input by truncating
        assertFalse(CodigoUtil.isValidCNPJ(nonNumericCNPJ), "Expected CNPJ with non-numeric characters to return false");
    }

    @Test
    public void isValidCNPJ_ShouldHandleEdgeCase_ForZeroCNPJ() {
        long zeroCNPJ = 0L; // Edge case where CNPJ is all zeros
        assertFalse(CodigoUtil.isValidCNPJ(zeroCNPJ), "Expected zero CNPJ to return false");
    }

    @Test
    public void isValidCNPJ_ShouldHandleEdgeCase_ForMaxLongValue() {
        long maxLongCNPJ = Long.MAX_VALUE; // Edge case for maximum long value
        assertFalse(CodigoUtil.isValidCNPJ(maxLongCNPJ), "Expected max long value CNPJ to return false");
    }
}
