package com.cafe.cafe.controller;


import com.cafe.cafe.domain.CoffeeGrade;
import com.cafe.cafe.domain.Order;
import com.cafe.cafe.dto.CoffeeGradeViewDTO;
import com.cafe.cafe.enums.OrderStatus;
import com.cafe.cafe.service.CafeMenuServiceImpl;
import com.cafe.cafe.service.OrderService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
@ViewScoped
@Data
public class CafeBean
{


    @ManagedProperty("value = #{cafeMenuService}")
    private final CafeMenuServiceImpl cafeMenuService;

    @ManagedProperty("value = #{orderService}")
    private final OrderService orderService;
    private Set<CoffeeGradeViewDTO> coffeeGradeView = new HashSet<>();


    public CafeBean(CafeMenuServiceImpl cafeMenuService, OrderService orderService)
    {
        this.cafeMenuService = cafeMenuService;
        this.orderService = orderService;
    }

    private List<Order> orders;

    public Set<CoffeeGradeViewDTO> getCoffeeGradeView()
    {
        return coffeeGradeView;
    }

    public List<Order> ordersWithStatus(String orderStatus)
    {
        return orderService.getAllOrdersByStatus(orderStatus);
    }

    public void cancelOrder(String orderId)
    {
        orderService.changeOrderStatus(orderId, OrderStatus.CANCELED);
    }

    public void closeOrder(String orderId)
    {
        orderService.changeOrderStatus(orderId, OrderStatus.CLOSED);
    }

}
