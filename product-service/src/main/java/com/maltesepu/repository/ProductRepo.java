package com.maltesepu.repository;

import com.maltesepu.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
