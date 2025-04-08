import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CodigoUtilTest {

    // Helper method to generate valid CNPJ for testing
    private long generateValidCNPJ() {
        return 12345678000195L; // Example of a valid CNPJ
    }

    // Helper method to generate invalid CNPJ for testing
    private long generateInvalidCNPJ() {
        return 12345678000196L; // Example of an invalid CNPJ
    }

    @Test
    public void isValidCNPJ_ShouldReturnTrue_ForValidCNPJ() {
        long validCNPJ = generateValidCNPJ();
        assertTrue(CodigoUtil.isValidCNPJ(validCNPJ), "Expected valid CNPJ to return true");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForInvalidCNPJ() {
        long invalidCNPJ = generateInvalidCNPJ();
        assertFalse(CodigoUtil.isValidCNPJ(invalidCNPJ), "Expected invalid CNPJ to return false");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForShortCNPJ() {
        long shortCNPJ = 12345678L; // CNPJ with less than 14 digits
        assertFalse(CodigoUtil.isValidCNPJ(shortCNPJ), "Expected short CNPJ to return false");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForLongCNPJ() {
        long longCNPJ = 123456789012345L; // CNPJ with more than 14 digits
        assertFalse(CodigoUtil.isValidCNPJ(longCNPJ), "Expected long CNPJ to return false");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForNonNumericCNPJ() {
        long nonNumericCNPJ = -12345678000195L; // Negative CNPJ
        assertFalse(CodigoUtil.isValidCNPJ(nonNumericCNPJ), "Expected non-numeric CNPJ to return false");
    }

    @Test
    public void isValidCNPJ_ShouldHandleEdgeCase_ForZeroCNPJ() {
        long zeroCNPJ = 0L; // CNPJ with all zeros
        assertFalse(CodigoUtil.isValidCNPJ(zeroCNPJ), "Expected zero CNPJ to return false");
    }

    @Test
    public void isValidCNPJ_ShouldHandleEdgeCase_ForMaxLongValue() {
        long maxLongCNPJ = Long.MAX_VALUE; // Maximum long value
        assertFalse(CodigoUtil.isValidCNPJ(maxLongCNPJ), "Expected max long value CNPJ to return false");
    }

    @Test
    public void isValidCNPJ_ShouldHandleEdgeCase_ForMinLongValue() {
        long minLongCNPJ = Long.MIN_VALUE; // Minimum long value
        assertFalse(CodigoUtil.isValidCNPJ(minLongCNPJ), "Expected min long value CNPJ to return false");
    }
}
