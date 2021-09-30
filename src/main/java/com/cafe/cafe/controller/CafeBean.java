package com.cafe.cafe.controller;


import com.cafe.cafe.domain.CoffeeGrade;
import com.cafe.cafe.domain.Order;
import com.cafe.cafe.enums.OrderStatus;
import com.cafe.cafe.service.CafeMenuService;
import com.cafe.cafe.service.OrderService;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.util.List;
import java.util.UUID;

@Component
@ViewScoped
@Data
public class CafeBean {

    private final CafeMenuService cafeMenuService;
    private final OrderService orderService;

    private List<Order> orders;

    public CafeBean(CafeMenuService cafeMenuService, OrderService orderService) {
        this.cafeMenuService = cafeMenuService;
        this.orderService = orderService;
    }

    public List<CoffeeGrade> getCafeMenu() {
        return cafeMenuService.getAllCoffeeGrades();
    }

    public List<Order> ordersWithStatus(String orderStatus) {
        return orderService.getAllOrdersByStatus(orderStatus);
    }

    public void cancelOrder(UUID orderId) {
        orderService.changeOrderStatus(orderId, OrderStatus.CANCELED);
    }

    public void closeOrder(UUID orderId) {
        orderService.changeOrderStatus(orderId, OrderStatus.CLOSED);
    }

}
