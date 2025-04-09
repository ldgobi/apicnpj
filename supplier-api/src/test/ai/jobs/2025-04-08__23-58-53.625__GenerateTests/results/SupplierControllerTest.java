import com.example.supplier.controller.SupplierController;
import com.example.supplier.model.Supplier;
import com.example.supplier.service.SupplierService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplierControllerTest {

    @Mock
    private SupplierService supplierService;

    @InjectMocks
    private SupplierController supplierController;

    public SupplierControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    private Supplier createMockSupplier(Long id, String name, String email) {
        Supplier supplier = new Supplier();
        supplier.setId(id);
        supplier.setName(name);
        supplier.setEmail(email);
        return supplier;
    }

    @Test
    void getAllSuppliers_ShouldReturnListOfSuppliers() {
        // Arrange
        List<Supplier> mockSuppliers = Arrays.asList(
                createMockSupplier(1L, "Supplier1", "supplier1@example.com"),
                createMockSupplier(2L, "Supplier2", "supplier2@example.com")
        );
        when(supplierService.getAllSuppliers()).thenReturn(mockSuppliers);

        // Act
        List<Supplier> result = supplierController.getAllSuppliers();

        // Assert
        assertEquals(mockSuppliers, result, "Expected list of suppliers to match the mock data");
        verify(supplierService, times(1)).getAllSuppliers();
    }

    @Test
    void getSupplierById_ShouldReturnSupplier_WhenSupplierExists() {
        // Arrange
        Supplier mockSupplier = createMockSupplier(1L, "Supplier1", "supplier1@example.com");
        when(supplierService.getSupplierById(1L)).thenReturn(Optional.of(mockSupplier));

        // Act
        ResponseEntity<Supplier> response = supplierController.getSupplierById(1L);

        // Assert
        assertTrue(response.getStatusCode().is2xxSuccessful(), "Expected HTTP status code to be 2xx");
        assertEquals(mockSupplier, response.getBody(), "Expected supplier to match the mock data");
        verify(supplierService, times(1)).getSupplierById(1L);
    }

    @Test
    void getSupplierById_ShouldReturnNotFound_WhenSupplierDoesNotExist() {
        // Arrange
        when(supplierService.getSupplierById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Supplier> response = supplierController.getSupplierById(1L);

        // Assert
        assertTrue(response.getStatusCode().is4xxClientError(), "Expected HTTP status code to be 4xx");
        assertNull(response.getBody(), "Expected response body to be null");
        verify(supplierService, times(1)).getSupplierById(1L);
    }

    @Test
    void createSupplier_ShouldReturnCreatedSupplier() {
        // Arrange
        Supplier mockSupplier = createMockSupplier(null, "Supplier1", "supplier1@example.com");
        Supplier savedSupplier = createMockSupplier(1L, "Supplier1", "supplier1@example.com");
        when(supplierService.createSupplier(mockSupplier)).thenReturn(savedSupplier);

        // Act
        Supplier result = supplierController.createSupplier(mockSupplier);

        // Assert
        assertEquals(savedSupplier, result, "Expected created supplier to match the saved supplier");
        verify(supplierService, times(1)).createSupplier(mockSupplier);
    }

    @Test
    void updateSupplier_ShouldReturnUpdatedSupplier_WhenSupplierExists() {
        // Arrange
        Supplier mockSupplierDetails = createMockSupplier(null, "UpdatedSupplier", "updated@example.com");
        Supplier updatedSupplier = createMockSupplier(1L, "UpdatedSupplier", "updated@example.com");
        when(supplierService.updateSupplier(1L, mockSupplierDetails)).thenReturn(updatedSupplier);

        // Act
        ResponseEntity<Supplier> response = supplierController.updateSupplier(1L, mockSupplierDetails);

        // Assert
        assertTrue(response.getStatusCode().is2xxSuccessful(), "Expected HTTP status code to be 2xx");
        assertEquals(updatedSupplier, response.getBody(), "Expected updated supplier to match the mock data");
        verify(supplierService, times(1)).updateSupplier(1L, mockSupplierDetails);
    }

    @Test
    void updateSupplier_ShouldReturnNotFound_WhenSupplierDoesNotExist() {
        // Arrange
        Supplier mockSupplierDetails = createMockSupplier(null, "UpdatedSupplier", "updated@example.com");
        when(supplierService.updateSupplier(1L, mockSupplierDetails)).thenReturn(null);

        // Act
        ResponseEntity<Supplier> response = supplierController.updateSupplier(1L, mockSupplierDetails);

        // Assert
        assertTrue(response.getStatusCode().is4xxClientError(), "Expected HTTP status code to be 4xx");
        assertNull(response.getBody(), "Expected response body to be null");
        verify(supplierService, times(1)).updateSupplier(1L, mockSupplierDetails);
    }

    @Test
    void deleteSupplier_ShouldReturnNoContent_WhenSupplierExists() {
        // Arrange
        when(supplierService.deleteSupplier(1L)).thenReturn(true);

        // Act
        ResponseEntity<Void> response = supplierController.deleteSupplier(1L);

        // Assert
        assertTrue(response.getStatusCode().is2xxSuccessful(), "Expected HTTP status code to be 2xx");
        assertNull(response.getBody(), "Expected response body to be null");
        verify(supplierService, times(1)).deleteSupplier(1L);
    }

    @Test
    void deleteSupplier_ShouldReturnNotFound_WhenSupplierDoesNotExist() {
        // Arrange
        when(supplierService.deleteSupplier(1L)).thenReturn(false);

        // Act
        ResponseEntity<Void> response = supplierController.deleteSupplier(1L);

        // Assert
        assertTrue(response.getStatusCode().is4xxClientError(), "Expected HTTP status code to be 4xx");
        assertNull(response.getBody(), "Expected response body to be null");
        verify(supplierService, times(1)).deleteSupplier(1L);
    }
}
