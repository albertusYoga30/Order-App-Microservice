package com.maltesepu.webclient;

import com.maltesepu.dto.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface ProductClient {

    @GetExchange("/api/products/{id}")
    public Product findById(@PathVariable("id") Long id);

}
