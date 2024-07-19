package com.maltesepu.service;

import com.maltesepu.entity.Product;
import com.maltesepu.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public Product findById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public Iterable<Product> findAll(){
        return productRepo.findAll();
    }

}
