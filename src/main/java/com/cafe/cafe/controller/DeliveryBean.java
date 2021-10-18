package com.cafe.cafe.controller;


import com.cafe.cafe.domain.Order;
import com.cafe.cafe.enums.DeliveryType;
import com.cafe.cafe.service.OrderService;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.IOException;

@Component
@ViewScoped
@Data
public class DeliveryBean
{

    @ManagedProperty("#{order}")
    private Order order;

    private Boolean validationDeliveryDetails = false;

    private Boolean validationDeliveryType = false;


    @ManagedProperty("#{orderService}")
    private final OrderService orderService;

    public DeliveryBean(OrderService orderService)
    {
        this.orderService = orderService;
    }


    public void confirmOrder() throws IOException
    {
        if(order.getDeliveryType() != null)
        {
            if((order.getCustomerName() == null || order.getDeliveryAddress() == null || order.getOrderDatetime() == null) && (order.getDeliveryType().equals(DeliveryType.DELIVERY)))
            {
                validationDeliveryDetails = true;
                validationDeliveryType = false;
            }
            else
            {
                order = orderService.confirmOrderByUser(order);
                validationDeliveryDetails = false;
                validationDeliveryType = false;
                FacesContext.getCurrentInstance().getExternalContext().redirect("/infoDelivery.jsf");
            }
        }
        else
        {
            validationDeliveryType = true;
            validationDeliveryDetails = false;
        }


    }

    public void showDialogMessage()
    {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('dlg2').show();");
    }


}
