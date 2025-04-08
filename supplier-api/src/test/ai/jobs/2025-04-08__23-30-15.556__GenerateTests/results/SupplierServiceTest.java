import com.example.supplier.model.Supplier;
import com.example.supplier.repository.SupplierRepository;
import com.example.supplier.service.SupplierService;
import com.example.supplier.util.CodigoUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SupplierServiceTest {

    @InjectMocks
    private SupplierService supplierService;

    @Mock
    private SupplierRepository supplierRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Supplier createMockSupplier(Long id, String nome, String cnpj, String nomeContato, String emailContato, String telefoneContato) {
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
    public void createSupplier_ShouldReturnSavedSupplier_WhenCNPJIsValid() {
        // Arrange
        Supplier supplier = createMockSupplier(null, "Supplier A", "12345678000195", "John Doe", "john@example.com", "123456789");
        when(CodigoUtil.isValidCNPJ(supplier.getCnpj())).thenReturn(true);
        when(supplierRepository.save(supplier)).thenReturn(supplier);

        // Act
        Supplier result = supplierService.createSupplier(supplier);

        // Assert
        assertNotNull(result, "The saved supplier should not be null");
        assertEquals(supplier.getNome(), result.getNome(), "The supplier name should match");
        verify(supplierRepository, times(1)).save(supplier);
    }

    @Test
    public void createSupplier_ShouldThrowException_WhenCNPJIsInvalid() {
        // Arrange
        Supplier supplier = createMockSupplier(null, "Supplier A", "invalidCNPJ", "John Doe", "john@example.com", "123456789");
        when(CodigoUtil.isValidCNPJ(supplier.getCnpj())).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> supplierService.createSupplier(supplier), "Expected exception for invalid CNPJ");
        assertEquals("Invalid CNPJ", exception.getMessage(), "Exception message should match");
        verify(supplierRepository, never()).save(any());
    }

    @Test
    public void getAllSuppliers_ShouldReturnListOfSuppliers() {
        // Arrange
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(createMockSupplier(1L, "Supplier A", "12345678000195", "John Doe", "john@example.com", "123456789"));
        suppliers.add(createMockSupplier(2L, "Supplier B", "98765432000195", "Jane Doe", "jane@example.com", "987654321"));
        when(supplierRepository.findAll()).thenReturn(suppliers);

        // Act
        List<Supplier> result = supplierService.getAllSuppliers();

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(2, result.size(), "The size of the supplier list should match");
        verify(supplierRepository, times(1)).findAll();
    }

    @Test
    public void getSupplierById_ShouldReturnSupplier_WhenSupplierExists() {
        // Arrange
        Supplier supplier = createMockSupplier(1L, "Supplier A", "12345678000195", "John Doe", "john@example.com", "123456789");
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));

        // Act
        Optional<Supplier> result = supplierService.getSupplierById(1L);

        // Assert
        assertTrue(result.isPresent(), "The supplier should be present");
        assertEquals(supplier.getNome(), result.get().getNome(), "The supplier name should match");
        verify(supplierRepository, times(1)).findById(1L);
    }

    @Test
    public void getSupplierById_ShouldReturnEmptyOptional_WhenSupplierDoesNotExist() {
        // Arrange
        when(supplierRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Optional<Supplier> result = supplierService.getSupplierById(1L);

        // Assert
        assertFalse(result.isPresent(), "The supplier should not be present");
        verify(supplierRepository, times(1)).findById(1L);
    }

    @Test
    public void updateSupplier_ShouldReturnUpdatedSupplier_WhenCNPJIsValid() {
        // Arrange
        Supplier existingSupplier = createMockSupplier(1L, "Supplier A", "12345678000195", "John Doe", "john@example.com", "123456789");
        Supplier updatedDetails = createMockSupplier(null, "Supplier A Updated", "12345678000195", "John Smith", "johnsmith@example.com", "987654321");
        when(CodigoUtil.isValidCNPJ(updatedDetails.getCnpj())).thenReturn(true);
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(existingSupplier));
        when(supplierRepository.save(existingSupplier)).thenReturn(existingSupplier);

        // Act
        Supplier result = supplierService.updateSupplier(1L, updatedDetails);

        // Assert
        assertNotNull(result, "The updated supplier should not be null");
        assertEquals(updatedDetails.getNome(), result.getNome(), "The supplier name should match the updated name");
        verify(supplierRepository, times(1)).findById(1L);
        verify(supplierRepository, times(1)).save(existingSupplier);
    }

    @Test
    public void updateSupplier_ShouldThrowException_WhenCNPJIsInvalid() {
        // Arrange
        Supplier updatedDetails = createMockSupplier(null, "Supplier A Updated", "invalidCNPJ", "John Smith", "johnsmith@example.com", "987654321");
        when(CodigoUtil.isValidCNPJ(updatedDetails.getCnpj())).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> supplierService.updateSupplier(1L, updatedDetails), "Expected exception for invalid CNPJ");
        assertEquals("Invalid CNPJ", exception.getMessage(), "Exception message should match");
        verify(supplierRepository, never()).save(any());
    }

    @Test
    public void updateSupplier_ShouldThrowException_WhenSupplierDoesNotExist() {
        // Arrange
        Supplier updatedDetails = createMockSupplier(null, "Supplier A Updated", "12345678000195", "John Smith", "johnsmith@example.com", "987654321");
        when(CodigoUtil.isValidCNPJ(updatedDetails.getCnpj())).thenReturn(true);
        when(supplierRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> supplierService.updateSupplier(1L, updatedDetails), "Expected exception for non-existent supplier");
        assertEquals("Supplier not found with id 1", exception.getMessage(), "Exception message should match");
        verify(supplierRepository, times(1)).findById(1L);
        verify(supplierRepository, never()).save(any());
    }

    @Test
    public void deleteSupplier_ShouldReturnTrue_WhenSupplierExists() {
        // Arrange
        Supplier supplier = createMockSupplier(1L, "Supplier A", "12345678000195", "John Doe", "john@example.com", "123456789");
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));

        // Act
        boolean result = supplierService.deleteSupplier(1L);

        // Assert
        assertTrue(result, "The delete operation should return true");
        verify(supplierRepository, times(1)).findById(1L);
        verify(supplierRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deleteSupplier_ShouldThrowException_WhenSupplierDoesNotExist() {
        // Arrange
        when(supplierRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> supplierService.deleteSupplier(1L), "Expected exception for non-existent supplier");
        assertEquals("Supplier not found with id 1", exception.getMessage(), "Exception message should match");
        verify(supplierRepository, times(1)).findById(1L);
        verify(supplierRepository, never()).deleteById(any());
    }
}
