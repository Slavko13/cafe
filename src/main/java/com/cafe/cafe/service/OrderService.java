package com.cafe.cafe.service;

import com.cafe.cafe.domain.Order;
import com.cafe.cafe.domain.OrderPoint;
import com.cafe.cafe.dto.OrderDTO;
import com.cafe.cafe.enums.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderService
{

    Order makeOrder(OrderDTO order);

    List<Order> getAllOrdersByStatus(String orderStatus);

    Order confirmOrderByUser(Order order);

    Order changeOrderStatus(String orderId, OrderStatus orderStatus);

}
