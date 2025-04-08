import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SupplierTest {

    // Helper method to create a Supplier instance for testing
    private Supplier createSupplier() {
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
        supplier.setId(10L);
        assertEquals(10L, supplier.getId(), "The ID should be set and retrieved correctly.");
    }

    @Test
    public void Supplier_SetAndGetNome_ShouldReturnCorrectValue() {
        Supplier supplier = new Supplier();
        supplier.setNome("Test Supplier");
        assertEquals("Test Supplier", supplier.getNome(), "The name should be set and retrieved correctly.");
    }

    @Test
    public void Supplier_SetAndGetCnpj_ShouldReturnCorrectValue() {
        Supplier supplier = new Supplier();
        supplier.setCnpj(98765432109876L);
        assertEquals(98765432109876L, supplier.getCnpj(), "The CNPJ should be set and retrieved correctly.");
    }

    @Test
    public void Supplier_SetAndGetNomeContato_ShouldReturnCorrectValue() {
        Supplier supplier = new Supplier();
        supplier.setNomeContato("Test Contact");
        assertEquals("Test Contact", supplier.getNomeContato(), "The contact name should be set and retrieved correctly.");
    }

    @Test
    public void Supplier_SetAndGetEmailContato_ShouldReturnCorrectValue() {
        Supplier supplier = new Supplier();
        supplier.setEmailContato("test@example.com");
        assertEquals("test@example.com", supplier.getEmailContato(), "The contact email should be set and retrieved correctly.");
    }

    @Test
    public void Supplier_SetAndGetTelefoneContato_ShouldReturnCorrectValue() {
        Supplier supplier = new Supplier();
        supplier.setTelefoneContato("987-654-3210");
        assertEquals("987-654-3210", supplier.getTelefoneContato(), "The contact phone number should be set and retrieved correctly.");
    }

    @Test
    public void Supplier_CreateSupplier_ShouldHaveCorrectValues() {
        Supplier supplier = createSupplier();
        assertAll("Supplier properties",
                () -> assertEquals(1L, supplier.getId(), "The ID should match the expected value."),
                () -> assertEquals("Supplier Name", supplier.getNome(), "The name should match the expected value."),
                () -> assertEquals(12345678901234L, supplier.getCnpj(), "The CNPJ should match the expected value."),
                () -> assertEquals("Contact Name", supplier.getNomeContato(), "The contact name should match the expected value."),
                () -> assertEquals("contact@example.com", supplier.getEmailContato(), "The contact email should match the expected value."),
                () -> assertEquals("123-456-7890", supplier.getTelefoneContato(), "The contact phone number should match the expected value.")
        );
    }

    @Test
    public void Supplier_SetNullValues_ShouldHandleGracefully() {
        Supplier supplier = new Supplier();
        supplier.setNome(null);
        supplier.setNomeContato(null);
        supplier.setEmailContato(null);
        supplier.setTelefoneContato(null);

        assertAll("Null values",
                () -> assertNull(supplier.getNome(), "The name should be null."),
                () -> assertNull(supplier.getNomeContato(), "The contact name should be null."),
                () -> assertNull(supplier.getEmailContato(), "The contact email should be null."),
                () -> assertNull(supplier.getTelefoneContato(), "The contact phone number should be null.")
        );
    }

    @Test
    public void Supplier_SetEmptyValues_ShouldHandleGracefully() {
        Supplier supplier = new Supplier();
        supplier.setNome("");
        supplier.setNomeContato("");
        supplier.setEmailContato("");
        supplier.setTelefoneContato("");

        assertAll("Empty values",
                () -> assertEquals("", supplier.getNome(), "The name should be empty."),
                () -> assertEquals("", supplier.getNomeContato(), "The contact name should be empty."),
                () -> assertEquals("", supplier.getEmailContato(), "The contact email should be empty."),
                () -> assertEquals("", supplier.getTelefoneContato(), "The contact phone number should be empty.")
        );
    }
}
