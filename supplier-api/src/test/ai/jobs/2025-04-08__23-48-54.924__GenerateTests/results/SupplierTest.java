import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SupplierTest {

    // Helper method to create a Supplier instance for reuse in tests
    private Supplier createSampleSupplier() {
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        supplier.setNome("Supplier Name");
        supplier.setCnpj(12345678901234L);
        supplier.setNomeContato("Contact Name");
        supplier.setEmailContato("contact@example.com");
        supplier.setTelefoneContato("123-456-7890");
        return supplier;
    }

    @Test
    public void Supplier_SetAndGetId_ShouldReturnCorrectValue() {
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        assertEquals(1L, supplier.getId(), "The ID should be correctly set and retrieved.");
    }

    @Test
    public void Supplier_SetAndGetNome_ShouldReturnCorrectValue() {
        Supplier supplier = new Supplier();
        supplier.setNome("Supplier Name");
        assertEquals("Supplier Name", supplier.getNome(), "The name should be correctly set and retrieved.");
    }

    @Test
    public void Supplier_SetAndGetCnpj_ShouldReturnCorrectValue() {
        Supplier supplier = new Supplier();
        supplier.setCnpj(12345678901234L);
        assertEquals(12345678901234L, supplier.getCnpj(), "The CNPJ should be correctly set and retrieved.");
    }

    @Test
    public void Supplier_SetAndGetNomeContato_ShouldReturnCorrectValue() {
        Supplier supplier = new Supplier();
        supplier.setNomeContato("Contact Name");
        assertEquals("Contact Name", supplier.getNomeContato(), "The contact name should be correctly set and retrieved.");
    }

    @Test
    public void Supplier_SetAndGetEmailContato_ShouldReturnCorrectValue() {
        Supplier supplier = new Supplier();
        supplier.setEmailContato("contact@example.com");
        assertEquals("contact@example.com", supplier.getEmailContato(), "The contact email should be correctly set and retrieved.");
    }

    @Test
    public void Supplier_SetAndGetTelefoneContato_ShouldReturnCorrectValue() {
        Supplier supplier = new Supplier();
        supplier.setTelefoneContato("123-456-7890");
        assertEquals("123-456-7890", supplier.getTelefoneContato(), "The contact phone should be correctly set and retrieved.");
    }

    @Test
    public void Supplier_AllFields_ShouldBeSetAndRetrievedCorrectly() {
        Supplier supplier = createSampleSupplier();

        assertAll("All fields should be correctly set and retrieved.",
                () -> assertEquals(1L, supplier.getId(), "The ID should match."),
                () -> assertEquals("Supplier Name", supplier.getNome(), "The name should match."),
                () -> assertEquals(12345678901234L, supplier.getCnpj(), "The CNPJ should match."),
                () -> assertEquals("Contact Name", supplier.getNomeContato(), "The contact name should match."),
                () -> assertEquals("contact@example.com", supplier.getEmailContato(), "The contact email should match."),
                () -> assertEquals("123-456-7890", supplier.getTelefoneContato(), "The contact phone should match.")
        );
    }

    @Test
    public void Supplier_DefaultConstructor_ShouldInitializeFieldsToNullOrDefault() {
        Supplier supplier = new Supplier();

        assertAll("Default constructor should initialize fields to null or default values.",
                () -> assertNull(supplier.getId(), "The ID should be null."),
                () -> assertNull(supplier.getNome(), "The name should be null."),
                () -> assertEquals(0L, supplier.getCnpj(), "The CNPJ should be 0."),
                () -> assertNull(supplier.getNomeContato(), "The contact name should be null."),
                () -> assertNull(supplier.getEmailContato(), "The contact email should be null."),
                () -> assertNull(supplier.getTelefoneContato(), "The contact phone should be null.")
        );
    }
}
