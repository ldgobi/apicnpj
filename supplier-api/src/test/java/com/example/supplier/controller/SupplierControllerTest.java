package com.example.supplier.controller;

import com.example.supplier.model.Supplier;
import com.example.supplier.service.SupplierService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SupplierController.class)
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.datasource.url=jdbc:h2:mem:test_controller;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
    "spring.datasource.username=sa",
    "spring.datasource.password="
})
public class SupplierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupplierService supplierService;

    @Autowired
    private ObjectMapper objectMapper;

    private Supplier supplier;
    private final Long supplierId = 1L;
    private final long validCnpj = 12345678000195L;

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
    public void getAllSuppliers_ShouldReturnListOfSuppliers() throws Exception {
        List<Supplier> suppliers = Arrays.asList(supplier);
        when(supplierService.getAllSuppliers()).thenReturn(suppliers);

        mockMvc.perform(get("/api/suppliers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(supplierId.intValue())))
                .andExpect(jsonPath("$[0].nome", is("Test Supplier")))
                .andExpect(jsonPath("$[0].cnpj", is(validCnpj)));

        verify(supplierService, times(1)).getAllSuppliers();
    }

    @Test
    public void getSupplierById_WhenSupplierExists_ShouldReturnSupplier() throws Exception {
        when(supplierService.getSupplierById(supplierId)).thenReturn(Optional.of(supplier));

        mockMvc.perform(get("/api/suppliers/{id}", supplierId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(supplierId.intValue())))
                .andExpect(jsonPath("$.nome", is("Test Supplier")))
                .andExpect(jsonPath("$.cnpj", is(validCnpj)));

        verify(supplierService, times(1)).getSupplierById(supplierId);
    }

    @Test
    public void getSupplierById_WhenSupplierDoesNotExist_ShouldReturnNotFound() throws Exception {
        when(supplierService.getSupplierById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/suppliers/{id}", 999))
                .andExpect(status().isNotFound());

        verify(supplierService, times(1)).getSupplierById(999L);
    }

    @Test
    public void createSupplier_ShouldReturnCreatedSupplier() throws Exception {
        when(supplierService.createSupplier(any(Supplier.class))).thenReturn(supplier);

        mockMvc.perform(post("/api/suppliers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supplier)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(supplierId.intValue())))
                .andExpect(jsonPath("$.nome", is("Test Supplier")))
                .andExpect(jsonPath("$.cnpj", is(validCnpj)));

        verify(supplierService, times(1)).createSupplier(any(Supplier.class));
    }

    @Test
    public void createSupplier_WithInvalidCNPJ_ShouldReturnBadRequest() throws Exception {
        when(supplierService.createSupplier(any(Supplier.class)))
                .thenThrow(new IllegalArgumentException("Invalid CNPJ"));

        mockMvc.perform(post("/api/suppliers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supplier)))
                .andExpect(status().isBadRequest());

        verify(supplierService, times(1)).createSupplier(any(Supplier.class));
    }

    @Test
    public void updateSupplier_WhenSupplierExists_ShouldReturnUpdatedSupplier() throws Exception {
        Supplier updatedSupplier = new Supplier();
        updatedSupplier.setId(supplierId);
        updatedSupplier.setNome("Updated Supplier");
        updatedSupplier.setCnpj(validCnpj);
        updatedSupplier.setNomeContato("Jane Doe");
        updatedSupplier.setEmailContato("jane@example.com");
        updatedSupplier.setTelefoneContato("987-654-3210");

        when(supplierService.updateSupplier(eq(supplierId), any(Supplier.class))).thenReturn(updatedSupplier);

        mockMvc.perform(put("/api/suppliers/{id}", supplierId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedSupplier)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(supplierId.intValue())))
                .andExpect(jsonPath("$.nome", is("Updated Supplier")))
                .andExpect(jsonPath("$.nomeContato", is("Jane Doe")));

        verify(supplierService, times(1)).updateSupplier(eq(supplierId), any(Supplier.class));
    }

    @Test
    public void updateSupplier_WhenSupplierDoesNotExist_ShouldReturnNotFound() throws Exception {
        when(supplierService.updateSupplier(eq(999L), any(Supplier.class)))
                .thenThrow(new RuntimeException("Supplier not found with id 999"));

        mockMvc.perform(put("/api/suppliers/{id}", 999)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supplier)))
                .andExpect(status().isNotFound());

        verify(supplierService, times(1)).updateSupplier(eq(999L), any(Supplier.class));
    }

    @Test
    public void updateSupplier_WithInvalidCNPJ_ShouldReturnBadRequest() throws Exception {
        when(supplierService.updateSupplier(eq(supplierId), any(Supplier.class)))
                .thenThrow(new IllegalArgumentException("Invalid CNPJ"));

        mockMvc.perform(put("/api/suppliers/{id}", supplierId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supplier)))
                .andExpect(status().isBadRequest());

        verify(supplierService, times(1)).updateSupplier(eq(supplierId), any(Supplier.class));
    }

    @Test
    public void deleteSupplier_WhenSupplierExists_ShouldReturnNoContent() throws Exception {
        when(supplierService.deleteSupplier(supplierId)).thenReturn(true);

        mockMvc.perform(delete("/api/suppliers/{id}", supplierId))
                .andExpect(status().isNoContent());

        verify(supplierService, times(1)).deleteSupplier(supplierId);
    }

    @Test
    public void deleteSupplier_WhenSupplierDoesNotExist_ShouldReturnNotFound() throws Exception {
        when(supplierService.deleteSupplier(999L)).thenReturn(false);

        mockMvc.perform(delete("/api/suppliers/{id}", 999))
                .andExpect(status().isNotFound());

        verify(supplierService, times(1)).deleteSupplier(999L);
    }
}
