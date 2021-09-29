package com.cafe.cafe.controller;


import com.cafe.cafe.domain.CoffeeGrade;
import com.cafe.cafe.domain.Order;
import com.cafe.cafe.domain.OrderPoint;
import com.cafe.cafe.service.OrderService;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ViewScoped
@Data
public class CartBean {

    private final DeliveryBean deliveryBean;
    private final OrderService orderService;
    private HashMap<Integer, Integer> selectedItems = new HashMap<>();
    private List<OrderPoint> orderPoints;
    private Boolean acceptOrderForDelivery = true;
    private Integer cupCounter;

    @PostConstruct
    public void init() {
        orderPoints = new ArrayList<>();
    }

    public CartBean(DeliveryBean deliveryBean, OrderService orderService) {
        this.deliveryBean = deliveryBean;
        this.orderService = orderService;
    }

    public void saveOrderPoint() throws IOException {

        if (acceptOrderForDelivery) {
            for (Map.Entry<Integer, Integer> entry : selectedItems.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                orderPoints.add(new OrderPoint(new CoffeeGrade(key), value));
            }
            deliveryBean.setOrder(orderService.makeOrder(new Order(orderPoints)));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/delivery.jsf");
        }
        else {
            return;
        }

        }

    public void addCups(Integer id){
        if(cupCounter > 0)
            selectedItems.put(id, cupCounter);
        if(cupCounter == 0)
            selectedItems.remove(id);
    }


    public void acceptOrder() {
        acceptOrderForDelivery = !acceptOrderForDelivery;
        System.out.println(acceptOrderForDelivery);
    }


}
