import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
//test
public class CodigoUtilTest {

    @Test
    public void isValidCNPJ_ShouldReturnTrue_ForValidCNPJ() {
        long validCNPJ = 12345678000195L; // Example valid CNPJ
        assertTrue(CodigoUtil.isValidCNPJ(validCNPJ), "Expected valid CNPJ to return true.");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForInvalidCNPJ() {
        long invalidCNPJ = 12345678000194L; // Example invalid CNPJ
        assertFalse(CodigoUtil.isValidCNPJ(invalidCNPJ), "Expected invalid CNPJ to return false.");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForCNPJWithIncorrectLength() {
        long shortCNPJ = 12345678L; // CNPJ with less than 14 digits
        assertFalse(CodigoUtil.isValidCNPJ(shortCNPJ), "Expected CNPJ with incorrect length to return false.");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForNegativeCNPJ() {
        long negativeCNPJ = -12345678000195L; // Negative CNPJ
        assertFalse(CodigoUtil.isValidCNPJ(negativeCNPJ), "Expected negative CNPJ to return false.");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForCNPJWithNonNumericCharacters() {
        long cnpjWithNonNumeric = Long.parseLong("12345678A00195".replaceAll("[^0-9]", "0")); // Simulating non-numeric characters
        assertFalse(CodigoUtil.isValidCNPJ(cnpjWithNonNumeric), "Expected CNPJ with non-numeric characters to return false.");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForCNPJWithAllZeros() {
        long allZerosCNPJ = 0L; // CNPJ with all zeros
        assertFalse(CodigoUtil.isValidCNPJ(allZerosCNPJ), "Expected CNPJ with all zeros to return false.");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForCNPJWithAllSameDigits() {
        long sameDigitsCNPJ = 11111111111111L; // CNPJ with all same digits
        assertFalse(CodigoUtil.isValidCNPJ(sameDigitsCNPJ), "Expected CNPJ with all same digits to return false.");
    }
}
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CodigoUtilTest {

    @Test
    public void isValidCNPJ_ShouldReturnTrue_ForValidCNPJ() {
        long validCNPJ = 12345678000195L; // Example valid CNPJ
        assertTrue(CodigoUtil.isValidCNPJ(validCNPJ), "Expected valid CNPJ to return true.");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForInvalidCNPJ() {
        long invalidCNPJ = 12345678000194L; // Example invalid CNPJ
        assertFalse(CodigoUtil.isValidCNPJ(invalidCNPJ), "Expected invalid CNPJ to return false.");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForCNPJWithIncorrectLength() {
        long shortCNPJ = 12345678L; // CNPJ with less than 14 digits
        assertFalse(CodigoUtil.isValidCNPJ(shortCNPJ), "Expected CNPJ with incorrect length to return false.");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForNegativeCNPJ() {
        long negativeCNPJ = -12345678000195L; // Negative CNPJ
        assertFalse(CodigoUtil.isValidCNPJ(negativeCNPJ), "Expected negative CNPJ to return false.");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForCNPJWithNonNumericCharacters() {
        long nonNumericCNPJ = Long.parseLong("12345678A00195".replaceAll("[A-Za-z]", "0")); // Simulating non-numeric CNPJ
        assertFalse(CodigoUtil.isValidCNPJ(nonNumericCNPJ), "Expected CNPJ with non-numeric characters to return false.");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForCNPJWithAllZeros() {
        long allZerosCNPJ = 00000000000000L; // CNPJ with all zeros
        assertFalse(CodigoUtil.isValidCNPJ(allZerosCNPJ), "Expected CNPJ with all zeros to return false.");
    }

    @Test
    public void isValidCNPJ_ShouldReturnFalse_ForCNPJWithAllSameDigits() {
        long sameDigitsCNPJ = 11111111111111L; // CNPJ with all same digits
        assertFalse(CodigoUtil.isValidCNPJ(sameDigitsCNPJ), "Expected CNPJ with all same digits to return false.");
    }
}
