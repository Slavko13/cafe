package com.cafe.cafe.controller;


import com.cafe.cafe.domain.CoffeeGrade;
import com.cafe.cafe.domain.Order;
import com.cafe.cafe.dto.OrderDTO;
import com.cafe.cafe.service.CafeMenuService;
import com.cafe.cafe.service.OrderService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
@Data
public class CafeBean {

    private final CafeMenuService cafeMenuService;
    private final OrderService orderService;
    private String deliveryAddress;

    public CafeBean(CafeMenuService cafeMenuService, OrderService orderService) {
        this.cafeMenuService = cafeMenuService;
        this.orderService = orderService;
    }

    public String getTest() {
        return "coffee";
    }

    public void makeOrder() {
        Order order = new Order();
        order.setDeliveryAddress(deliveryAddress);
        orderService.makeOrder(order);
    }

    public List<CoffeeGrade> getCafeMenu() {
        return cafeMenuService.getAllCoffeeGrades();
    }




}
