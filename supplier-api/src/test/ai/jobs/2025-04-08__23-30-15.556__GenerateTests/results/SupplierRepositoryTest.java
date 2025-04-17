import com.example.supplier.model.Supplier;
import com.example.supplier.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class SupplierRepositoryTest {

    @Autowired
    private SupplierRepository supplierRepository;

    private Supplier supplier;

    @BeforeEach
    public void setUp() {
        // Initialize a Supplier object for testing
        supplier = new Supplier();
        supplier.setName("Test Supplier");
        supplier.setEmail("test@supplier.com");
        supplier.setPhone("1234567890");
    }

    @Test
    public void save_ShouldPersistSupplier() {
        // Act
        Supplier savedSupplier = supplierRepository.save(supplier);

        // Assert
        assertTrue(savedSupplier.getId() > 0, "Saved supplier should have a valid ID");
        assertEquals(supplier.getName(), savedSupplier.getName(), "Supplier name should match");
        assertEquals(supplier.getEmail(), savedSupplier.getEmail(), "Supplier email should match");
        assertEquals(supplier.getPhone(), savedSupplier.getPhone(), "Supplier phone should match");
    }

    @Test
    public void findById_ShouldReturnSupplier_WhenSupplierExists() {
        // Arrange
        Supplier savedSupplier = supplierRepository.save(supplier);

        // Act
        Optional<Supplier> foundSupplier = supplierRepository.findById(savedSupplier.getId());

        // Assert
        assertTrue(foundSupplier.isPresent(), "Supplier should be found by ID");
        assertEquals(savedSupplier.getId(), foundSupplier.get().getId(), "Found supplier ID should match");
    }

    @Test
    public void findById_ShouldReturnEmpty_WhenSupplierDoesNotExist() {
        // Act
        Optional<Supplier> foundSupplier = supplierRepository.findById(999L);

        // Assert
        assertTrue(foundSupplier.isEmpty(), "No supplier should be found for a non-existent ID");
    }

    @Test
    public void deleteById_ShouldRemoveSupplier() {
        // Arrange
        Supplier savedSupplier = supplierRepository.save(supplier);

        // Act
        supplierRepository.deleteById(savedSupplier.getId());
        Optional<Supplier> foundSupplier = supplierRepository.findById(savedSupplier.getId());

        // Assert
        assertTrue(foundSupplier.isEmpty(), "Supplier should be deleted and not found");
    }

    @Test
    public void findAll_ShouldReturnAllSuppliers() {
        // Arrange
        Supplier supplier1 = new Supplier();
        supplier1.setName("Supplier 1");
        supplier1.setEmail("supplier1@supplier.com");
        supplier1.setPhone("1111111111");

        Supplier supplier2 = new Supplier();
        supplier2.setName("Supplier 2");
        supplier2.setEmail("supplier2@supplier.com");
        supplier2.setPhone("2222222222");

        supplierRepository.save(supplier);
        supplierRepository.save(supplier1);
        supplierRepository.save(supplier2);

        // Act
        Iterable<Supplier> suppliers = supplierRepository.findAll();

        // Assert
        assertTrue(((Collection<?>) suppliers).size() >= 3, "Should return all suppliers");
    }
}
