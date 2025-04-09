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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private CodigoUtil codigoUtil;

    @InjectMocks
    private SupplierService supplierService;

    private Supplier supplier;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        supplier = new Supplier();
        supplier.setId(1L);
        supplier.setNome("Supplier Name");
        supplier.setCnpj("12345678901234");
        supplier.setNomeContato("Contact Name");
        supplier.setEmailContato("contact@example.com");
        supplier.setTelefoneContato("1234567890");
    }

    @Test
    public void createSupplier_ShouldSaveSupplier_WhenCNPJIsValid() {
        when(CodigoUtil.isValidCNPJ(supplier.getCnpj())).thenReturn(true);
        when(supplierRepository.save(supplier)).thenReturn(supplier);

        Supplier createdSupplier = supplierService.createSupplier(supplier);

        assertNotNull(createdSupplier, "Created supplier should not be null");
        assertEquals(supplier.getNome(), createdSupplier.getNome(), "Supplier name should match");
        verify(supplierRepository, times(1)).save(supplier);
    }

    @Test
    public void createSupplier_ShouldThrowException_WhenCNPJIsInvalid() {
        when(CodigoUtil.isValidCNPJ(supplier.getCnpj())).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            supplierService.createSupplier(supplier);
        });

        assertEquals("Invalid CNPJ", exception.getMessage(), "Exception message should match");
        verify(supplierRepository, never()).save(supplier);
    }

    @Test
    public void getAllSuppliers_ShouldReturnListOfSuppliers() {
        List<Supplier> suppliers = Arrays.asList(supplier);
        when(supplierRepository.findAll()).thenReturn(suppliers);

        List<Supplier> result = supplierService.getAllSuppliers();

        assertNotNull(result, "Result should not be null");
        assertEquals(1, result.size(), "Result size should match");
        assertEquals(supplier.getNome(), result.get(0).getNome(), "Supplier name should match");
        verify(supplierRepository, times(1)).findAll();
    }

    @Test
    public void getSupplierById_ShouldReturnSupplier_WhenSupplierExists() {
        when(supplierRepository.findById(supplier.getId())).thenReturn(Optional.of(supplier));

        Optional<Supplier> result = supplierService.getSupplierById(supplier.getId());

        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(supplier.getNome(), result.get().getNome(), "Supplier name should match");
        verify(supplierRepository, times(1)).findById(supplier.getId());
    }

    @Test
    public void getSupplierById_ShouldReturnEmpty_WhenSupplierDoesNotExist() {
        when(supplierRepository.findById(supplier.getId())).thenReturn(Optional.empty());

        Optional<Supplier> result = supplierService.getSupplierById(supplier.getId());

        assertFalse(result.isPresent(), "Result should not be present");
        verify(supplierRepository, times(1)).findById(supplier.getId());
    }

    @Test
    public void updateSupplier_ShouldUpdateSupplier_WhenCNPJIsValid() {
        Supplier updatedDetails = new Supplier();
        updatedDetails.setNome("Updated Name");
        updatedDetails.setCnpj("98765432109876");
        updatedDetails.setNomeContato("Updated Contact");
        updatedDetails.setEmailContato("updated@example.com");
        updatedDetails.setTelefoneContato("0987654321");

        when(CodigoUtil.isValidCNPJ(updatedDetails.getCnpj())).thenReturn(true);
        when(supplierRepository.findById(supplier.getId())).thenReturn(Optional.of(supplier));
        when(supplierRepository.save(supplier)).thenReturn(supplier);

        Supplier updatedSupplier = supplierService.updateSupplier(supplier.getId(), updatedDetails);

        assertNotNull(updatedSupplier, "Updated supplier should not be null");
        assertEquals(updatedDetails.getNome(), updatedSupplier.getNome(), "Updated name should match");
        verify(supplierRepository, times(1)).findById(supplier.getId());
        verify(supplierRepository, times(1)).save(supplier);
    }

    @Test
    public void updateSupplier_ShouldThrowException_WhenCNPJIsInvalid() {
        Supplier updatedDetails = new Supplier();
        updatedDetails.setCnpj("invalidCNPJ");

        when(CodigoUtil.isValidCNPJ(updatedDetails.getCnpj())).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            supplierService.updateSupplier(supplier.getId(), updatedDetails);
        });

        assertEquals("Invalid CNPJ", exception.getMessage(), "Exception message should match");
        verify(supplierRepository, never()).save(supplier);
    }

    @Test
    public void updateSupplier_ShouldThrowException_WhenSupplierDoesNotExist() {
        Supplier updatedDetails = new Supplier();
        updatedDetails.setCnpj("98765432109876");

        when(CodigoUtil.isValidCNPJ(updatedDetails.getCnpj())).thenReturn(true);
        when(supplierRepository.findById(supplier.getId())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            supplierService.updateSupplier(supplier.getId(), updatedDetails);
        });

        assertEquals("Supplier not found with id " + supplier.getId(), exception.getMessage(), "Exception message should match");
        verify(supplierRepository, times(1)).findById(supplier.getId());
        verify(supplierRepository, never()).save(supplier);
    }

    @Test
    public void deleteSupplier_ShouldDeleteSupplier_WhenSupplierExists() {
        when(supplierRepository.findById(supplier.getId())).thenReturn(Optional.of(supplier));

        boolean result = supplierService.deleteSupplier(supplier.getId());

        assertTrue(result, "Result should be true");
        verify(supplierRepository, times(1)).findById(supplier.getId());
        verify(supplierRepository, times(1)).deleteById(supplier.getId());
    }

    @Test
    public void deleteSupplier_ShouldThrowException_WhenSupplierDoesNotExist() {
        when(supplierRepository.findById(supplier.getId())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            supplierService.deleteSupplier(supplier.getId());
        });

        assertEquals("Supplier not found with id " + supplier.getId(), exception.getMessage(), "Exception message should match");
        verify(supplierRepository, times(1)).findById(supplier.getId());
        verify(supplierRepository, never()).deleteById(supplier.getId());
    }
}
