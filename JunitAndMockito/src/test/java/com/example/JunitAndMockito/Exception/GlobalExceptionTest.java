package com.example.JunitAndMockito.Exception;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class GlobalExceptionTest {

    private GlobalException globalException;

    @BeforeEach
    void setUp() {
        globalException = new GlobalException();
    }

    @Test
    void testProductNotFoundException() {
        // Given
        ProductNotFoundException exception = new ProductNotFoundException("Product not found in DB");

        // When
        ResponseEntity<String> response = globalException.exception(exception);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Product not found in DB", response.getBody());
    }

    @Test
    void testProductNotFoundExceptionWithDefaultMessage() {
        // Given
        ProductNotFoundException exception = new ProductNotFoundException();

        // When
        ResponseEntity<String> response = globalException.exception(exception);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Product not found", response.getBody());
    }
}



