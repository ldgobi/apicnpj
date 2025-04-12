import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CodigoUtilTest {

    // Helper method to generate valid CNPJ for testing
    private long generateValidCNPJ() {
        return 12345678000195L; // Example valid CNPJ
    }

    // Helper method to generate invalid CNPJ for testing
    private long generateInvalidCNPJ() {
        return 12345678000196L; // Example invalid CNPJ
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
        long shortCNPJ = 12345678L; // Example short CNPJ
        assertFalse(CodigoUtil.isValidCNPJ(shortCNPJ), "Expected short CNPJ to return false");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForLongCNPJ() {
        long longCNPJ = 123456789012345L; // Example long CNPJ
        assertFalse(CodigoUtil.isValidCNPJ(longCNPJ), "Expected long CNPJ to return false");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForNegativeCNPJ() {
        long negativeCNPJ = -12345678000195L; // Example negative CNPJ
        assertFalse(CodigoUtil.isValidCNPJ(negativeCNPJ), "Expected negative CNPJ to return false");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForNonNumericCNPJ() {
        long nonNumericCNPJ = 12345678L; // Simulating non-numeric CNPJ by truncating
        assertFalse(CodigoUtil.isValidCNPJ(nonNumericCNPJ), "Expected non-numeric CNPJ to return false");
    }

    @Test
    public void isValidCNPJ_ShouldHandleEdgeCase_ForZeroCNPJ() {
        long zeroCNPJ = 0L; // Edge case: CNPJ with all zeros
        assertFalse(CodigoUtil.isValidCNPJ(zeroCNPJ), "Expected zero CNPJ to return false");
    }

    @Test
    public void isValidCNPJ_ShouldHandleEdgeCase_ForMaxLongValue() {
        long maxLongCNPJ = Long.MAX_VALUE; // Edge case: Maximum long value
        assertFalse(CodigoUtil.isValidCNPJ(maxLongCNPJ), "Expected max long value CNPJ to return false");
    }
}
