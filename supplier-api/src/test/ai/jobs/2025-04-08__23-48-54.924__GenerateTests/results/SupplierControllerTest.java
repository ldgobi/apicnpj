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

    @Mock
    private SupplierService supplierService;

    @InjectMocks
    private SupplierController supplierController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllSuppliers_ShouldReturnListOfSuppliers() {
        // Arrange
        List<Supplier> mockSuppliers = new ArrayList<>();
        mockSuppliers.add(new Supplier(1L, "Supplier1", "Address1"));
        mockSuppliers.add(new Supplier(2L, "Supplier2", "Address2"));
        when(supplierService.getAllSuppliers()).thenReturn(mockSuppliers);

        // Act
        List<Supplier> result = supplierController.getAllSuppliers();

        // Assert
        assertEquals(2, result.size(), "Expected size of supplier list to be 2");
        assertEquals("Supplier1", result.get(0).getName(), "Expected first supplier name to be 'Supplier1'");
        assertEquals("Supplier2", result.get(1).getName(), "Expected second supplier name to be 'Supplier2'");
        verify(supplierService, times(1)).getAllSuppliers();
    }

    @Test
    void getSupplierById_ShouldReturnSupplier_WhenSupplierExists() {
        // Arrange
        Supplier mockSupplier = new Supplier(1L, "Supplier1", "Address1");
        when(supplierService.getSupplierById(1L)).thenReturn(Optional.of(mockSupplier));

        // Act
        ResponseEntity<Supplier> response = supplierController.getSupplierById(1L);

        // Assert
        assertTrue(response.getStatusCode().is2xxSuccessful(), "Expected HTTP status code to be 2xx");
        assertEquals(mockSupplier, response.getBody(), "Expected response body to match mock supplier");
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
        Supplier mockSupplier = new Supplier(null, "Supplier1", "Address1");
        Supplier createdSupplier = new Supplier(1L, "Supplier1", "Address1");
        when(supplierService.createSupplier(mockSupplier)).thenReturn(createdSupplier);

        // Act
        Supplier response = supplierController.createSupplier(mockSupplier);

        // Assert
        assertEquals(createdSupplier, response, "Expected response to match created supplier");
        verify(supplierService, times(1)).createSupplier(mockSupplier);
    }

    @Test
    void updateSupplier_ShouldReturnUpdatedSupplier_WhenSupplierExists() {
        // Arrange
        Supplier mockSupplierDetails = new Supplier(null, "UpdatedSupplier", "UpdatedAddress");
        Supplier updatedSupplier = new Supplier(1L, "UpdatedSupplier", "UpdatedAddress");
        when(supplierService.updateSupplier(1L, mockSupplierDetails)).thenReturn(updatedSupplier);

        // Act
        ResponseEntity<Supplier> response = supplierController.updateSupplier(1L, mockSupplierDetails);

        // Assert
        assertTrue(response.getStatusCode().is2xxSuccessful(), "Expected HTTP status code to be 2xx");
        assertEquals(updatedSupplier, response.getBody(), "Expected response body to match updated supplier");
        verify(supplierService, times(1)).updateSupplier(1L, mockSupplierDetails);
    }

    @Test
    void updateSupplier_ShouldReturnNotFound_WhenSupplierDoesNotExist() {
        // Arrange
        Supplier mockSupplierDetails = new Supplier(null, "UpdatedSupplier", "UpdatedAddress");
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
