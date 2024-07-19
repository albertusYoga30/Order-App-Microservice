package com.maltesepu.service;

import com.maltesepu.dto.Customer;
import com.maltesepu.dto.OrderLineResponse;
import com.maltesepu.dto.OrderResponse;
import com.maltesepu.dto.Product;
import com.maltesepu.entity.Order;
import com.maltesepu.entity.OrderLine;
import com.maltesepu.repository.OrderRepo;
import com.maltesepu.webclient.CustomerClient;
import com.maltesepu.webclient.ProductClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private ProductClient productClient;

    public Order save(Order order) {
        for (OrderLine orderLine : order.getOrderLines()) {
            orderLine.setOrder(order);
        }
        return orderRepo.save(order);
    }

    public OrderResponse findById(Long id) {
        Optional<Order> optOrder = orderRepo.findById(id);
        if (!optOrder.isPresent()) {
            return null;
        }
        Order order = optOrder.get();
        OrderResponse response = new OrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getOrderDate(),
                customerClient.findById(order.getCustomerId()),
                new ArrayList<OrderLineResponse>()
        );

        for (OrderLine orderLine : order.getOrderLines()) {
            Product product = productClient.findById(orderLine.getProductId());
            response.getOrderLines().add(new OrderLineResponse(
                    orderLine.getId(), product, orderLine.getQuantity(), orderLine.getPrice())
            );
        }

        return response;
    }

    public OrderResponse findByOrderNumber(String orderNumber) {
        Order order = orderRepo.findByOrderNumber(orderNumber);
        if (order == null) {
            return null;
        }

        OrderResponse response = new OrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getOrderDate(),
                customerClient.findById(order.getCustomerId()),
                new ArrayList<OrderLineResponse>()
        );

        for (OrderLine orderLine : order.getOrderLines()) {
            Product product = productClient.findById(orderLine.getProductId());
            response.getOrderLines().add(new OrderLineResponse(
                    orderLine.getId(), product, orderLine.getQuantity(), orderLine.getPrice())
            );
        }

        return response;
    }

//    public Customer findCustomerById(Long id) {
//        return restTemplate.getForObject("http://CUSTOMER-SERVICE/api/customers/" + id, Customer.class);
//    }
//
//    public Product findProductById(Long id) {
//        return restTemplate.getForObject("http://PRODUCT-SERVICE/api/products/" + id, Product.class);
//    }

}
