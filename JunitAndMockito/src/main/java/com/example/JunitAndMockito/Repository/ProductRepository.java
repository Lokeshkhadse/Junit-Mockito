package com.example.JunitAndMockito.Repository;

import com.example.JunitAndMockito.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
