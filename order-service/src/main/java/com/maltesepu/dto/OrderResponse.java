package com.maltesepu.dto;

import com.maltesepu.entity.OrderLine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Long id;
    private String orderNumber;
    private Date orderDate;
    private Customer customer;
    private List<OrderLineResponse> orderLines;
}
