package com.cafe.cafe.controller;


import com.cafe.cafe.domain.Order;
import com.cafe.cafe.enums.DeliveryType;
import com.cafe.cafe.service.OrderService;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.IOException;

@ManagedBean
@ViewScoped
@Data
public class DeliveryBean {

    @ManagedProperty("#{order}")
    private Order order;

    public Order getOrder() {
        return order;
    }

    @ManagedProperty("#{orderService}")
    private final OrderService orderService;

    public DeliveryBean(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setOrder(Order order) {
        this.order=order;
    }

    public void confirmOrder() throws IOException {
        if ((order.getCustomerName() == null || order.getDeliveryAddress() == null || order.getOrderDatetime() == null) && order.getDeliveryType().equals(DeliveryType.DELIVERY) ) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Вы не выбрали напитки!",
                    "Message details");
            context.addMessage(null, message);
            context.validationFailed();
        }
        else {
            order = orderService.confirmOrderByUser(order);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/infoDelivery.jsf");
        }

    }

    public void showDialogMessage() {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('dlg2').show();");
    }


}
