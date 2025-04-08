import com.example.supplier.model.Supplier;
import com.example.supplier.repository.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") // Ensures the test profile is used
public class SupplierRepositoryTest {

    @Autowired
    private SupplierRepository supplierRepository;

    // Helper method to create a sample Supplier
    private Supplier createSampleSupplier(String name) {
        Supplier supplier = new Supplier();
        supplier.setName(name);
        return supplierRepository.save(supplier);
    }

    @Test
    public void SupplierRepository_Save_ShouldPersistSupplier() {
        // Arrange
        Supplier supplier = new Supplier();
        supplier.setName("Test Supplier");

        // Act
        Supplier savedSupplier = supplierRepository.save(supplier);

        // Assert
        assertNotNull(savedSupplier.getId(), "Saved supplier should have an ID");
        assertEquals("Test Supplier", savedSupplier.getName(), "Saved supplier name should match");
    }

    @Test
    public void SupplierRepository_FindById_ShouldReturnSupplier() {
        // Arrange
        Supplier savedSupplier = createSampleSupplier("Find Test Supplier");

        // Act
        Optional<Supplier> foundSupplier = supplierRepository.findById(savedSupplier.getId());

        // Assert
        assertTrue(foundSupplier.isPresent(), "Supplier should be found by ID");
        assertEquals("Find Test Supplier", foundSupplier.get().getName(), "Found supplier name should match");
    }

    @Test
    public void SupplierRepository_FindById_ShouldReturnEmptyOptionalForNonExistentId() {
        // Act
        Optional<Supplier> foundSupplier = supplierRepository.findById(999L);

        // Assert
        assertFalse(foundSupplier.isPresent(), "Supplier should not be found for non-existent ID");
    }

    @Test
    public void SupplierRepository_Delete_ShouldRemoveSupplier() {
        // Arrange
        Supplier savedSupplier = createSampleSupplier("Delete Test Supplier");

        // Act
        supplierRepository.delete(savedSupplier);
        Optional<Supplier> foundSupplier = supplierRepository.findById(savedSupplier.getId());

        // Assert
        assertFalse(foundSupplier.isPresent(), "Supplier should be deleted and not found");
    }

    @Test
    public void SupplierRepository_FindAll_ShouldReturnAllSuppliers() {
        // Arrange
        createSampleSupplier("Supplier 1");
        createSampleSupplier("Supplier 2");

        // Act
        Iterable<Supplier> suppliers = supplierRepository.findAll();

        // Assert
        assertTrue(suppliers.iterator().hasNext(), "FindAll should return all suppliers");
    }
}
