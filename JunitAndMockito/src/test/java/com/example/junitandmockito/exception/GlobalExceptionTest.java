package com.example.junitandmockito.exception;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class GlobalExceptionTest {

    private GlobalExceptionHandler globalException;

    @BeforeEach
    void setUp() {
        globalException = new GlobalExceptionHandler();
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



    @Test
    void testOverloadException() {
        // Given
        OverloadException overloadException = new OverloadException("overloaded product");

        // When
        ResponseEntity<String> response = globalException.overload(overloadException);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("overloaded product", response.getBody());
    }

    @Test
    void testOverloadExceptionWithDefaultMessage() {
        // Given
        OverloadException overloadException = new OverloadException();

        // When                                       //method name in globalException class
        ResponseEntity<String> response = globalException.overload(overloadException);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("overloaded product", response.getBody());
    }

}



