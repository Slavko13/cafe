package com.cafe.cafe.controller;


import com.cafe.cafe.domain.CoffeeGrade;
import com.cafe.cafe.domain.OrderPoint;
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
    private HashMap<Integer, Integer> selectedItems = new HashMap<>();
    private List<OrderPoint> orderPoints;
    private Boolean acceptOrderForDelivery = false;

    private Integer cupCounter;

    @PostConstruct
    public void init() {
        orderPoints = new ArrayList<>();
    }

    public CartBean(DeliveryBean deliveryBean) {
        this.deliveryBean = deliveryBean;
    }

    public void saveOrderPoint() throws IOException {

        if (acceptOrderForDelivery == true) {
            for (Map.Entry<Integer, Integer> entry : selectedItems.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                orderPoints.add(new OrderPoint(new CoffeeGrade(key), value));
            }
            deliveryBean.setOrderPointsFromCart(orderPoints);
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
