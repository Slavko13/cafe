package com.cafe.cafe.controller;


import com.cafe.cafe.domain.Order;
import com.cafe.cafe.service.OrderService;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.IOException;

@ManagedBean
@Scope(value = "session")
@Data
public class DeliveryBean {

    @ManagedProperty("#{order}")
    private Order order;


    @ManagedProperty("#{orderService}")
    private final OrderService orderService;

    public DeliveryBean(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setOrder(Order order) {
        this.order=order;
    }

    public void confirmOrder() throws IOException {
        order = orderService.confirmOrderByUser(order);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/infoDelivery.jsf");
    }
}
