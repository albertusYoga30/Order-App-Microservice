package com.maltesepu.repository;

import com.maltesepu.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {

    Order findByOrderNumber(String orderNumber);
}
