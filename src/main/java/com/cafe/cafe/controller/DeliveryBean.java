package com.cafe.cafe.controller;


import com.cafe.cafe.domain.Order;
import com.cafe.cafe.domain.OrderPoint;
import com.cafe.cafe.service.OrderService;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Component
@ViewScoped
@Data
public class DeliveryBean {

    @ManagedProperty("#{order}")
    private Order order;

    private final OrderService orderService;

    public DeliveryBean(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setOrder(Order order) {
        this.order=order;
    }

    public Order confirmOrder() {
        return orderService.confirmOrder(order);
    }
}
