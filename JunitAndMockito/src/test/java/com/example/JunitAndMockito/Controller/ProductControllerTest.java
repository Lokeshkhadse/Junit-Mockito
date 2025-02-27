package com.example.JunitAndMockito.Controller;

import com.example.JunitAndMockito.Entity.Product;
import com.example.JunitAndMockito.Service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build(); // Manually setting up controller
    }

    // ✅ 1. Test Add Product (POST)

    @Test
    void testAddProduct() throws Exception{
        Product product = new Product(1L,"laptop","Gaming",1500.0,5);
        when(productService.addProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("laptop"));

    }


    // ✅ 2. Test Get All Products (GET)
    @Test
    void testGetAllProduct() throws Exception {
        List<Product> products = Arrays.asList(new Product(1L, "Laptop", "Gaming", 1500.0, 5));
        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/api/products")
                       .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("@[0].name").value("Laptop"));
    }


//    // ✅ 3. Test Get Product By ID (GET)
    @Test
    void testProductById() throws Exception{
        Product product = new Product(1L,"Laptop","Gaming",1500.0,5);
        when(productService.getProductById(1L)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/api/products/1")
                       .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"));

    }

//    // ✅ 4. Test Update Product (PUT)
    @Test
    void testUpdatedProduct() throws Exception{

        Product updatedProduct = new Product(1L,"updatedLapy","highlelvel",1500.0,5);

        when(productService.updateProduct(any(Long.class), any(Product.class))).thenReturn(updatedProduct);

        mockMvc.perform(put("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("updatedLapy"))
                .andExpect(jsonPath("@.price").value(1500.0));
    }


    // ✅ 5. Test Delete Product (DELETE)
    @Test
    void testDeleteProduct() throws Exception{
        doNothing().when(productService).deleteProduct(1L);

        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Product deleted successfully"));
    }

}

