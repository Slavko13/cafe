package com.cafe.cafe.controller;


import com.cafe.cafe.domain.CoffeeGrade;
import com.cafe.cafe.domain.Order;
import com.cafe.cafe.domain.OrderPoint;
import com.cafe.cafe.service.CafeMenuService;
import com.cafe.cafe.service.OrderService;
import lombok.Data;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
@Scope(value = "session")
@Data
public class CartBean {


    private final DeliveryBean deliveryBean;
    private final OrderService orderService;
    private final CafeMenuService cafeMenuService;
    private HashMap<Integer, Integer> selectedItems = new HashMap<>();
    private List<OrderPoint> orderPoints;
    private Boolean acceptOrderForDelivery = false;
    private Integer cupCounter = 0;
    private Integer possiblePrice = 0;
    private Integer fullPrice = 0;
    private List<CoffeeGrade> enabledItems = new ArrayList<>();

    @Value("${inventory.free.cup.number}")
    private Integer freeCupNumber;


    @PostConstruct
    public void init() {
        orderPoints = new ArrayList<>();
    }

    public CartBean(DeliveryBean deliveryBean, OrderService orderService, CafeMenuService cafeMenuService) {
        this.deliveryBean = deliveryBean;
        this.orderService = orderService;
        this.cafeMenuService = cafeMenuService;
    }

    public void saveOrderPoint() throws IOException {


        for (Map.Entry<Integer, Integer> entry : selectedItems.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            orderPoints.add(new OrderPoint(new CoffeeGrade(key), value));
        }
        if (orderPoints.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Вы не выбрали напитки!",
                    "Message details");
            context.addMessage(null, message);
            context.validationFailed();
        }
        else {
            deliveryBean.setOrder(orderService.makeOrder(new Order(orderPoints)));
            selectedItems.clear();
            orderPoints.clear();
            cupCounter = 0;
            possiblePrice = 0;
            fullPrice = 0;
            FacesContext.getCurrentInstance().getExternalContext().redirect("/delivery.jsf");
        }

    }

    public void addCups(Integer id){
        if(cupCounter > 0) {
            selectedItems.put(id, cupCounter);
            HashMap<Integer, Integer> possiblePromotion = cafeMenuService.calculatePossiblePrice(selectedItems);
            for (Map.Entry<Integer, Integer> entry : possiblePromotion.entrySet()) {
                possiblePrice = entry.getKey();
                fullPrice = entry.getValue();
            }

        }

        if(cupCounter == 0)
            selectedItems.remove(id);
    }


    public void acceptOrder() {
        acceptOrderForDelivery = !acceptOrderForDelivery;
        System.out.println(acceptOrderForDelivery);
    }





    public void onRowSelectCheckbox(SelectEvent<CoffeeGrade> event) {

    }


    public Boolean checkEnable(CoffeeGrade item) {
        return enabledItems.contains(item);
    }
}
