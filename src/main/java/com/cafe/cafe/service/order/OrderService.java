package com.cafe.cafe.service.order;


import com.cafe.cafe.domain.order.Order;
import com.cafe.cafe.dto.OrderDTO;
import com.cafe.cafe.enums.order.OrderStatus;


import java.util.List;

public interface OrderService
{

    Order makeOrder(OrderDTO order);

    List<Order> getAllOrdersByStatus(String orderStatus);

    Order confirmOrderByUser(Order order);

    Order changeOrderStatus(String orderId, OrderStatus orderStatus);

}
