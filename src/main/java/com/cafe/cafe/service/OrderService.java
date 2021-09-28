package com.cafe.cafe.service;

import com.cafe.cafe.domain.Order;

import java.util.List;

public interface OrderService {

    Order makeOrder(Order order);
    List<Order> getAllOrders();

}
