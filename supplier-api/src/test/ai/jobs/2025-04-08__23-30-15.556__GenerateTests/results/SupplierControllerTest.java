import com.example.supplier.controller.SupplierController;
import com.example.supplier.model.Supplier;
import com.example.supplier.service.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplierControllerTest {

    @InjectMocks
    private SupplierController supplierController;

    @Mock
    private SupplierService supplierService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Supplier createSampleSupplier(Long id, String name, String email) {
        Supplier supplier = new Supplier();
        supplier.setId(id);
        supplier.setName(name);
        supplier.setEmail(email);
        return supplier;
    }

    @Test
    void getAllSuppliers_ShouldReturnListOfSuppliers() {
        // Arrange
        List<Supplier> mockSuppliers = new ArrayList<>();
        mockSuppliers.add(createSampleSupplier(1L, "Supplier1", "supplier1@example.com"));
        mockSuppliers.add(createSampleSupplier(2L, "Supplier2", "supplier2@example.com"));
        when(supplierService.getAllSuppliers()).thenReturn(mockSuppliers);

        // Act
        List<Supplier> result = supplierController.getAllSuppliers();

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(2, result.size(), "The size of the result list should be 2");
        verify(supplierService, times(1)).getAllSuppliers();
    }

    @Test
    void getSupplierById_ShouldReturnSupplier_WhenSupplierExists() {
        // Arrange
        Supplier mockSupplier = createSampleSupplier(1L, "Supplier1", "supplier1@example.com");
        when(supplierService.getSupplierById(1L)).thenReturn(Optional.of(mockSupplier));

        // Act
        ResponseEntity<Supplier> response = supplierController.getSupplierById(1L);

        // Assert
        assertNotNull(response, "The response should not be null");
        assertEquals(200, response.getStatusCodeValue(), "The status code should be 200");
        assertEquals(mockSupplier, response.getBody(), "The response body should match the mock supplier");
        verify(supplierService, times(1)).getSupplierById(1L);
    }

    @Test
    void getSupplierById_ShouldReturnNotFound_WhenSupplierDoesNotExist() {
        // Arrange
        when(supplierService.getSupplierById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Supplier> response = supplierController.getSupplierById(1L);

        // Assert
        assertNotNull(response, "The response should not be null");
        assertEquals(404, response.getStatusCodeValue(), "The status code should be 404");
        assertNull(response.getBody(), "The response body should be null");
        verify(supplierService, times(1)).getSupplierById(1L);
    }

    @Test
    void createSupplier_ShouldReturnCreatedSupplier() {
        // Arrange
        Supplier mockSupplier = createSampleSupplier(null, "Supplier1", "supplier1@example.com");
        Supplier savedSupplier = createSampleSupplier(1L, "Supplier1", "supplier1@example.com");
        when(supplierService.createSupplier(mockSupplier)).thenReturn(savedSupplier);

        // Act
        Supplier result = supplierController.createSupplier(mockSupplier);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(savedSupplier, result, "The result should match the saved supplier");
        verify(supplierService, times(1)).createSupplier(mockSupplier);
    }

    @Test
    void updateSupplier_ShouldReturnUpdatedSupplier_WhenSupplierExists() {
        // Arrange
        Supplier mockSupplierDetails = createSampleSupplier(null, "UpdatedSupplier", "updated@example.com");
        Supplier updatedSupplier = createSampleSupplier(1L, "UpdatedSupplier", "updated@example.com");
        when(supplierService.updateSupplier(1L, mockSupplierDetails)).thenReturn(updatedSupplier);

        // Act
        ResponseEntity<Supplier> response = supplierController.updateSupplier(1L, mockSupplierDetails);

        // Assert
        assertNotNull(response, "The response should not be null");
        assertEquals(200, response.getStatusCodeValue(), "The status code should be 200");
        assertEquals(updatedSupplier, response.getBody(), "The response body should match the updated supplier");
        verify(supplierService, times(1)).updateSupplier(1L, mockSupplierDetails);
    }

    @Test
    void updateSupplier_ShouldReturnNotFound_WhenSupplierDoesNotExist() {
        // Arrange
        Supplier mockSupplierDetails = createSampleSupplier(null, "UpdatedSupplier", "updated@example.com");
        when(supplierService.updateSupplier(1L, mockSupplierDetails)).thenReturn(null);

        // Act
        ResponseEntity<Supplier> response = supplierController.updateSupplier(1L, mockSupplierDetails);

        // Assert
        assertNotNull(response, "The response should not be null");
        assertEquals(404, response.getStatusCodeValue(), "The status code should be 404");
        assertNull(response.getBody(), "The response body should be null");
        verify(supplierService, times(1)).updateSupplier(1L, mockSupplierDetails);
    }

    @Test
    void deleteSupplier_ShouldReturnNoContent_WhenSupplierExists() {
        // Arrange
        when(supplierService.deleteSupplier(1L)).thenReturn(true);

        // Act
        ResponseEntity<Void> response = supplierController.deleteSupplier(1L);

        // Assert
        assertNotNull(response, "The response should not be null");
        assertEquals(204, response.getStatusCodeValue(), "The status code should be 204");
        assertNull(response.getBody(), "The response body should be null");
        verify(supplierService, times(1)).deleteSupplier(1L);
    }

    @Test
    void deleteSupplier_ShouldReturnNotFound_WhenSupplierDoesNotExist() {
        // Arrange
        when(supplierService.deleteSupplier(1L)).thenReturn(false);

        // Act
        ResponseEntity<Void> response = supplierController.deleteSupplier(1L);

        // Assert
        assertNotNull(response, "The response should not be null");
        assertEquals(404, response.getStatusCodeValue(), "The status code should be 404");
        assertNull(response.getBody(), "The response body should be null");
        verify(supplierService, times(1)).deleteSupplier(1L);
    }
}
