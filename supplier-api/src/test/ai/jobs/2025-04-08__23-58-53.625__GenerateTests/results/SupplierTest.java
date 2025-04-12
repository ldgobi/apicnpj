import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SupplierTests {

    // Helper method to create a Supplier instance for testing
    private Supplier createSupplier(Long id, String nome, long cnpj, String nomeContato, String emailContato, String telefoneContato) {
        Supplier supplier = new Supplier();
        supplier.setId(id);
        supplier.setNome(nome);
        supplier.setCnpj(cnpj);
        supplier.setNomeContato(nomeContato);
        supplier.setEmailContato(emailContato);
        supplier.setTelefoneContato(telefoneContato);
        return supplier;
    }

    @Test
    public void Supplier_SetAndGetId_ShouldReturnCorrectValue() {
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        assertEquals(1L, supplier.getId(), "Supplier ID should be correctly set and retrieved.");
    }

    @Test
    public void Supplier_SetAndGetNome_ShouldReturnCorrectValue() {
        Supplier supplier = new Supplier();
        supplier.setNome("Supplier Name");
        assertEquals("Supplier Name", supplier.getNome(), "Supplier name should be correctly set and retrieved.");
    }

    @Test
    public void Supplier_SetAndGetCnpj_ShouldReturnCorrectValue() {
        Supplier supplier = new Supplier();
        supplier.setCnpj(12345678901234L);
        assertEquals(12345678901234L, supplier.getCnpj(), "Supplier CNPJ should be correctly set and retrieved.");
    }

    @Test
    public void Supplier_SetAndGetNomeContato_ShouldReturnCorrectValue() {
        Supplier supplier = new Supplier();
        supplier.setNomeContato("Contact Name");
        assertEquals("Contact Name", supplier.getNomeContato(), "Supplier contact name should be correctly set and retrieved.");
    }

    @Test
    public void Supplier_SetAndGetEmailContato_ShouldReturnCorrectValue() {
        Supplier supplier = new Supplier();
        supplier.setEmailContato("contact@example.com");
        assertEquals("contact@example.com", supplier.getEmailContato(), "Supplier contact email should be correctly set and retrieved.");
    }

    @Test
    public void Supplier_SetAndGetTelefoneContato_ShouldReturnCorrectValue() {
        Supplier supplier = new Supplier();
        supplier.setTelefoneContato("123-456-7890");
        assertEquals("123-456-7890", supplier.getTelefoneContato(), "Supplier contact phone should be correctly set and retrieved.");
    }

    @Test
    public void Supplier_CreateSupplier_ShouldHaveCorrectValues() {
        Supplier supplier = createSupplier(1L, "Supplier Name", 12345678901234L, "Contact Name", "contact@example.com", "123-456-7890");

        assertEquals(1L, supplier.getId(), "Supplier ID should match the expected value.");
        assertEquals("Supplier Name", supplier.getNome(), "Supplier name should match the expected value.");
        assertEquals(12345678901234L, supplier.getCnpj(), "Supplier CNPJ should match the expected value.");
        assertEquals("Contact Name", supplier.getNomeContato(), "Supplier contact name should match the expected value.");
        assertEquals("contact@example.com", supplier.getEmailContato(), "Supplier contact email should match the expected value.");
        assertEquals("123-456-7890", supplier.getTelefoneContato(), "Supplier contact phone should match the expected value.");
    }

    @Test
    public void Supplier_SetNullValues_ShouldHandleGracefully() {
        Supplier supplier = new Supplier();
        supplier.setNome(null);
        supplier.setNomeContato(null);
        supplier.setEmailContato(null);
        supplier.setTelefoneContato(null);

        assertNull(supplier.getNome(), "Supplier name should be null.");
        assertNull(supplier.getNomeContato(), "Supplier contact name should be null.");
        assertNull(supplier.getEmailContato(), "Supplier contact email should be null.");
        assertNull(supplier.getTelefoneContato(), "Supplier contact phone should be null.");
    }

    @Test
    public void Supplier_SetEmptyValues_ShouldHandleGracefully() {
        Supplier supplier = new Supplier();
        supplier.setNome("");
        supplier.setNomeContato("");
        supplier.setEmailContato("");
        supplier.setTelefoneContato("");

        assertEquals("", supplier.getNome(), "Supplier name should be empty.");
        assertEquals("", supplier.getNomeContato(), "Supplier contact name should be empty.");
        assertEquals("", supplier.getEmailContato(), "Supplier contact email should be empty.");
        assertEquals("", supplier.getTelefoneContato(), "Supplier contact phone should be empty.");
    }
}
