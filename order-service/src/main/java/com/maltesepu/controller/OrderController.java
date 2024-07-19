package com.maltesepu.controller;

import com.maltesepu.dto.OrderResponse;
import com.maltesepu.entity.Order;
import com.maltesepu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    public OrderService orderService;

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable("id") Long id) {
        return orderService.findById(id);
    }

    @GetMapping("/order-number/{number}")
    public OrderResponse findByOrderNumber(@PathVariable("number") String number) {
        return orderService.findByOrderNumber(number);
    }

    @PostMapping
    public Order save(@RequestBody Order order) {
        return orderService.save(order);
    }
}
