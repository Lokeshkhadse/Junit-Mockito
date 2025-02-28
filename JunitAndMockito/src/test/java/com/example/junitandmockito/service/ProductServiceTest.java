package com.example.junitandmockito.service;

import com.example.junitandmockito.entity.Product;
import com.example.junitandmockito.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    private ProductRepository productRepository;

   private Product product;

    @BeforeAll()
    public static void BeforeAllMethod(){
        System.out.println("before all ********");
    }

    @BeforeEach
    public void BeforeEachSetProduct(){
        product = new Product();
        product.setId(1L);
        product.setName("bag");
        product.setDescription("bag description");
        product.setPrice(300.00);
        product.setQuantity(10);

    }

    @Test
     void testAddProduct(){
        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.addProduct(product);

        assertEquals("bag",savedProduct.getName());
        assertNotNull(savedProduct);
        assertEquals(product,savedProduct);
    }

    @Test
    void testGetAllProduct(){
        when(productRepository.findAll()).thenReturn(List.of(product));

        List<Product> allPrdouct = productRepository.findAll();

        assertEquals(1,allPrdouct.size());
    }

    @Test
    void testProductById(){
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> getProduct = productService.getProductById(1L);

        assertTrue(getProduct.isPresent());
        assertEquals("bag", getProduct.get().getName());
        assertFalse(getProduct.isEmpty());
        System.out.println("checking");

    }

    @Test
    void testUpdateProduct(){
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Product updateProduct = productService.updateProduct(1L,product);
        assertEquals("bag",updateProduct.getName());
    }

    @Test
    void testDeleteProduct(){
        doNothing().when(productRepository).deleteById(1L);

         productService.deleteProduct(1L);

         verify(productRepository,times(1)).deleteById(1L);
    }

    @AfterEach
    void afterEach(){
        System.out.println("after each test");
    }

}
