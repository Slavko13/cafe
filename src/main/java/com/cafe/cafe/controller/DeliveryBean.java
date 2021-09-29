package com.cafe.cafe.controller;


import com.cafe.cafe.domain.OrderPoint;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Component
@ViewScoped
@Data
public class DeliveryBean {

    private Date orderDatetime;
    private String customerName;
    private String deliveryAddress;
    private Integer fullOrderPrice;
    private Boolean acceptOrder;

    private List<OrderPoint> orderPoints;

    public void setOrderPointsFromCart(List<OrderPoint> orderPointsFromCart) {
        orderPoints = orderPointsFromCart;
    }

}
