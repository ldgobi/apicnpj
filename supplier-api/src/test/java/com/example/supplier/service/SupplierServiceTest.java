package com.example.supplier.service;

import com.example.supplier.model.Supplier;
import com.example.supplier.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.datasource.url=jdbc:h2:mem:test_service;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
    "spring.datasource.username=sa",
    "spring.datasource.password="
})
public class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierService supplierService;

    private Supplier supplier;
    private final Long supplierId = 1L;
    private final long validCnpj = 12345678000195L; // Valid CNPJ for testing
    private final long invalidCnpj = 12345678000196L; // Invalid CNPJ for testing

    @BeforeEach
    public void setup() {
        supplier = new Supplier();
        supplier.setId(supplierId);
        supplier.setNome("Test Supplier");
        supplier.setCnpj(validCnpj);
        supplier.setNomeContato("John Doe");
        supplier.setEmailContato("john@example.com");
        supplier.setTelefoneContato("123-456-7890");
    }

    @Test
    public void createSupplier_WithValidCNPJ_ShouldReturnSupplier() {
        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);

        Supplier createdSupplier = supplierService.createSupplier(supplier);

        assertNotNull(createdSupplier);
        assertEquals(supplierId, createdSupplier.getId());
        assertEquals("Test Supplier", createdSupplier.getNome());
        assertEquals(validCnpj, createdSupplier.getCnpj());
        verify(supplierRepository, times(1)).save(any(Supplier.class));
    }

    @Test
    public void createSupplier_WithInvalidCNPJ_ShouldThrowException() {
        supplier.setCnpj(invalidCnpj);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            supplierService.createSupplier(supplier);
        });

        assertEquals("Invalid CNPJ", exception.getMessage());
        verify(supplierRepository, never()).save(any(Supplier.class));
    }

    @Test
    public void getAllSuppliers_ShouldReturnListOfSuppliers() {
        List<Supplier> suppliers = Arrays.asList(supplier);
        when(supplierRepository.findAll()).thenReturn(suppliers);

        List<Supplier> result = supplierService.getAllSuppliers();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(supplierRepository, times(1)).findAll();
    }

    @Test
    public void getSupplierById_WhenSupplierExists_ShouldReturnSupplier() {
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));

        Optional<Supplier> result = supplierService.getSupplierById(supplierId);

        assertTrue(result.isPresent());
        assertEquals(supplierId, result.get().getId());
        verify(supplierRepository, times(1)).findById(supplierId);
    }

    @Test
    public void getSupplierById_WhenSupplierDoesNotExist_ShouldReturnEmptyOptional() {
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Supplier> result = supplierService.getSupplierById(999L);

        assertFalse(result.isPresent());
        verify(supplierRepository, times(1)).findById(999L);
    }

    @Test
    public void updateSupplier_WithValidCNPJ_ShouldReturnUpdatedSupplier() {
        Supplier updatedSupplier = new Supplier();
        updatedSupplier.setNome("Updated Supplier");
        updatedSupplier.setCnpj(validCnpj);
        updatedSupplier.setNomeContato("Jane Doe");
        updatedSupplier.setEmailContato("jane@example.com");
        updatedSupplier.setTelefoneContato("987-654-3210");

        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));
        when(supplierRepository.save(any(Supplier.class))).thenReturn(updatedSupplier);

        Supplier result = supplierService.updateSupplier(supplierId, updatedSupplier);

        assertNotNull(result);
        assertEquals("Updated Supplier", result.getNome());
        assertEquals("Jane Doe", result.getNomeContato());
        verify(supplierRepository, times(1)).findById(supplierId);
        verify(supplierRepository, times(1)).save(any(Supplier.class));
    }

    @Test
    public void updateSupplier_WithInvalidCNPJ_ShouldThrowException() {
        Supplier updatedSupplier = new Supplier();
        updatedSupplier.setCnpj(invalidCnpj);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            supplierService.updateSupplier(supplierId, updatedSupplier);
        });

        assertEquals("Invalid CNPJ", exception.getMessage());
        verify(supplierRepository, never()).save(any(Supplier.class));
    }

    @Test
    public void updateSupplier_WhenSupplierDoesNotExist_ShouldThrowException() {
        Supplier updatedSupplier = new Supplier();
        updatedSupplier.setCnpj(validCnpj);

        when(supplierRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            supplierService.updateSupplier(999L, updatedSupplier);
        });

        assertEquals("Supplier not found with id 999", exception.getMessage());
        verify(supplierRepository, times(1)).findById(999L);
        verify(supplierRepository, never()).save(any(Supplier.class));
    }

    @Test
    public void deleteSupplier_WhenSupplierExists_ShouldReturnTrue() {
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));
        doNothing().when(supplierRepository).deleteById(supplierId);

        boolean result = supplierService.deleteSupplier(supplierId);

        assertTrue(result);
        verify(supplierRepository, times(1)).findById(supplierId);
        verify(supplierRepository, times(1)).deleteById(supplierId);
    }

    @Test
    public void deleteSupplier_WhenSupplierDoesNotExist_ShouldThrowException() {
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            supplierService.deleteSupplier(999L);
        });

        assertEquals("Supplier not found with id 999", exception.getMessage());
        verify(supplierRepository, times(1)).findById(999L);
        verify(supplierRepository, never()).deleteById(anyLong());
    }
}
