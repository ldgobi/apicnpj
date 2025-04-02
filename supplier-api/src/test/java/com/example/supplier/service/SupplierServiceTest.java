package com.example.supplier.service;

import com.example.supplier.service.SupplierService;
import com.example.supplier.model.Supplier;
import com.example.supplier.repository.SupplierRepository;
import com.example.supplier.util.CodigoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SupplierServiceTest {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private SupplierRepository supplierRepository;

    // Placeholder for Batch 1 tests
            // Test for createSupplier method
        @Test
        public void createSupplier_ShouldSaveSupplier_WhenCNPJIsValid() {
            Supplier supplier = new Supplier();
            supplier.setCnpj(12345678);
            supplier.setNome("Supplier Name");
    
            when(CodigoUtil.isValidCNPJ(12345678)).thenReturn(true);
            when(supplierRepository.save(supplier)).thenReturn(supplier);
    
            Supplier savedSupplier = supplierService.createSupplier(supplier);
    
            assertNotNull(savedSupplier, "Saved supplier should not be null");
            assertEquals("Supplier Name", savedSupplier.getNome(), "Supplier name should match");
            verify(supplierRepository, times(1)).save(supplier);
        }
    
        @Test
        public void createSupplier_ShouldThrowException_WhenCNPJIsInvalid() {
            Supplier supplier = new Supplier();
            supplier.setCnpj(12345678);
    
            when(CodigoUtil.isValidCNPJ(12345678)).thenReturn(false);
    
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                supplierService.createSupplier(supplier);
            });
    
            assertEquals("Invalid CNPJ", exception.getMessage(), "Exception message should match");
            verify(supplierRepository, never()).save(any(Supplier.class));
        }
    
        // Test for getAllSuppliers method
        @Test
        public void getAllSuppliers_ShouldReturnListOfSuppliers() {
            List<Supplier> suppliers = List.of(new Supplier(), new Supplier());
            when(supplierRepository.findAll()).thenReturn(suppliers);
    
            List<Supplier> result = supplierService.getAllSuppliers();
    
            assertNotNull(result, "Result should not be null");
            assertEquals(2, result.size(), "Result size should match");
            verify(supplierRepository, times(1)).findAll();
        }
    
        // Test for getSupplierById method
        @Test
        public void getSupplierById_ShouldReturnSupplier_WhenSupplierExists() {
            Supplier supplier = new Supplier();
            supplier.setId(1L);
            when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
    
            Optional<Supplier> result = supplierService.getSupplierById(1L);
    
            assertTrue(result.isPresent(), "Result should be present");
            assertEquals(1L, result.get().getId(), "Supplier ID should match");
            verify(supplierRepository, times(1)).findById(1L);
        }
    
        @Test
        public void getSupplierById_ShouldReturnEmpty_WhenSupplierDoesNotExist() {
            when(supplierRepository.findById(1L)).thenReturn(Optional.empty());
    
            Optional<Supplier> result = supplierService.getSupplierById(1L);
    
            assertFalse(result.isPresent(), "Result should not be present");
            verify(supplierRepository, times(1)).findById(1L);
        }
    
        // Test for updateSupplier method
        @Test
        public void updateSupplier_ShouldUpdateSupplier_WhenCNPJIsValid() {
            Supplier existingSupplier = new Supplier();
            existingSupplier.setId(1L);
            existingSupplier.setCnpj(12345678);
    
            Supplier updatedDetails = new Supplier();
            updatedDetails.setCnpj(12345678);
            updatedDetails.setNome("Updated Name");
    
            when(supplierRepository.findById(1L)).thenReturn(Optional.of(existingSupplier));
            when(CodigoUtil.isValidCNPJ(12345678)).thenReturn(true);
            when(supplierRepository.save(existingSupplier)).thenReturn(existingSupplier);
    
            Supplier result = supplierService.updateSupplier(1L, updatedDetails);
    
            assertNotNull(result, "Result should not be null");
            assertEquals("Updated Name", result.getNome(), "Supplier name should match");
            verify(supplierRepository, times(1)).findById(1L);
            verify(supplierRepository, times(1)).save(existingSupplier);
        }
    
        @Test
        public void updateSupplier_ShouldThrowException_WhenCNPJIsInvalid() {
            Supplier updatedDetails = new Supplier();
            updatedDetails.setCnpj(12345678);
    
            when(CodigoUtil.isValidCNPJ(12345678)).thenReturn(false);
    
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                supplierService.updateSupplier(1L, updatedDetails);
            });
    
            assertEquals("Invalid CNPJ", exception.getMessage(), "Exception message should match");
            verify(supplierRepository, never()).save(any(Supplier.class));
        }
    
        @Test
        public void updateSupplier_ShouldThrowException_WhenSupplierDoesNotExist() {
            Supplier updatedDetails = new Supplier();
            updatedDetails.setCnpj(12345678);
    
            when(supplierRepository.findById(1L)).thenReturn(Optional.empty());
    
            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                supplierService.updateSupplier(1L, updatedDetails);
            });
    
            assertEquals("Supplier not found with id 1", exception.getMessage(), "Exception message should match");
            verify(supplierRepository, never()).save(any(Supplier.class));
        }
    
        // Test for deleteSupplier method
        @Test
        public void deleteSupplier_ShouldDeleteSupplier_WhenSupplierExists() {
            Supplier supplier = new Supplier();
            supplier.setId(1L);
    
            when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
    
            boolean result = supplierService.deleteSupplier(1L);
    
            assertTrue(result, "Result should be true");
            verify(supplierRepository, times(1)).findById(1L);
            verify(supplierRepository, times(1)).deleteById(1L);
        }
    
        @Test
        public void deleteSupplier_ShouldThrowException_WhenSupplierDoesNotExist() {
            when(supplierRepository.findById(1L)).thenReturn(Optional.empty());
    
            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                supplierService.deleteSupplier(1L);
            });
    
            assertEquals("Supplier not found with id 1", exception.getMessage(), "Exception message should match");
            verify(supplierRepository, never()).deleteById(anyLong());
        }

    // Placeholder for Batch 2 tests
            // Test for CodigoUtil.isValidCNPJ method
        @Test
        public void isValidCNPJ_ShouldReturnTrue_WhenCNPJIsValid() {
            long validCNPJ = 12345678000195l; // Example of a valid CNPJ
    
            boolean result = CodigoUtil.isValidCNPJ(validCNPJ);
    
            assertTrue(result, "Result should be true for a valid CNPJ");
        }
    
        @Test
        public void isValidCNPJ_ShouldReturnFalse_WhenCNPJIsInvalid() {
            long invalidCNPJ = 12345678; // Example of an invalid CNPJ
    
            boolean result = CodigoUtil.isValidCNPJ(invalidCNPJ);
    
            assertFalse(result, "Result should be false for an invalid CNPJ");
        }
    
        @Test
        public void isValidCNPJ_ShouldReturnFalse_WhenCNPJIsNull() {
            Long nullCNPJ = null;
    
            boolean result = CodigoUtil.isValidCNPJ(nullCNPJ);
    
            assertFalse(result, "Result should be false for a null CNPJ");
        }
    
//        @Test
//        public void isValidCNPJ_ShouldReturnFalse_WhenCNPJIsEmpty() {
//            String emptyCNPJ = "";
//    
//            boolean result = CodigoUtil.isValidCNPJ(emptyCNPJ);
//    
//            assertFalse(result, "Result should be false for an empty CNPJ");
//        }
}
