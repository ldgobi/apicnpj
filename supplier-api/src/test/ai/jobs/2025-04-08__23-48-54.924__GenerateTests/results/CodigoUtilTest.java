import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CodigoUtilTest {

    // Helper method to generate valid CNPJ for testing
    private long generateValidCNPJ() {
        return 12345678000195L; // Replace with a valid CNPJ for testing
    }

    @Test
    public void isValidCNPJ_ShouldReturnTrue_ForValidCNPJ() {
        // Arrange
        long validCNPJ = generateValidCNPJ();

        // Act
        boolean result = CodigoUtil.isValidCNPJ(validCNPJ);

        // Assert
        assertTrue(result, "Expected valid CNPJ to return true");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForInvalidCNPJ() {
        // Arrange
        long invalidCNPJ = 12345678000196L; // Invalid CNPJ

        // Act
        boolean result = CodigoUtil.isValidCNPJ(invalidCNPJ);

        // Assert
        assertFalse(result, "Expected invalid CNPJ to return false");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForCNPJWithLessThan14Digits() {
        // Arrange
        long shortCNPJ = 12345678L; // CNPJ with less than 14 digits

        // Act
        boolean result = CodigoUtil.isValidCNPJ(shortCNPJ);

        // Assert
        assertFalse(result, "Expected CNPJ with less than 14 digits to return false");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForCNPJWithMoreThan14Digits() {
        // Arrange
        long longCNPJ = 123456789012345L; // CNPJ with more than 14 digits

        // Act
        boolean result = CodigoUtil.isValidCNPJ(longCNPJ);

        // Assert
        assertFalse(result, "Expected CNPJ with more than 14 digits to return false");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForCNPJWithInvalidChecksum() {
        // Arrange
        long invalidChecksumCNPJ = 12345678000194L; // CNPJ with invalid checksum

        // Act
        boolean result = CodigoUtil.isValidCNPJ(invalidChecksumCNPJ);

        // Assert
        assertFalse(result, "Expected CNPJ with invalid checksum to return false");
    }

    @Test
    public void isValidCNPJ_ShouldHandleEdgeCase_ForAllZeros() {
        // Arrange
        long allZerosCNPJ = 0L; // CNPJ with all zeros

        // Act
        boolean result = CodigoUtil.isValidCNPJ(allZerosCNPJ);

        // Assert
        assertFalse(result, "Expected CNPJ with all zeros to return false");
    }

    @Test
    public void isValidCNPJ_ShouldHandleEdgeCase_ForAllNines() {
        // Arrange
        long allNinesCNPJ = 99999999999999L; // CNPJ with all nines

        // Act
        boolean result = CodigoUtil.isValidCNPJ(allNinesCNPJ);

        // Assert
        assertFalse(result, "Expected CNPJ with all nines to return false");
    }

    @Test
    public void isValidCNPJ_ShouldHandleEdgeCase_ForNegativeCNPJ() {
        // Arrange
        long negativeCNPJ = -12345678000195L; // Negative CNPJ

        // Act
        boolean result = CodigoUtil.isValidCNPJ(negativeCNPJ);

        // Assert
        assertFalse(result, "Expected negative CNPJ to return false");
    }

    @Test
    public void isValidCNPJ_ShouldHandleEdgeCase_ForNonNumericCNPJ() {
        // Arrange
        long nonNumericCNPJ = Long.parseLong("12345678ABCD95", 16); // Simulating non-numeric input

        // Act
        boolean result = CodigoUtil.isValidCNPJ(nonNumericCNPJ);

        // Assert
        assertFalse(result, "Expected non-numeric CNPJ to return false");
    }
}
