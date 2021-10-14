package com.cafe.cafe.controller;


import com.cafe.cafe.domain.CoffeeGrade;
import com.cafe.cafe.domain.Order;
import com.cafe.cafe.domain.OrderPoint;
import com.cafe.cafe.dto.CoffeeGradeViewDTO;
import com.cafe.cafe.service.CafeMenuServiceImpl;
import com.cafe.cafe.service.OrderService;
import lombok.Data;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.faces.view.ViewScoped;
import java.io.IOException;
import java.util.*;

@Component
@Data
@ViewScoped
public class CartBean {

    @ManagedProperty(value = "deliveryBean")
    private final DeliveryBean deliveryBean;
    @ManagedProperty(value = "orderService")
    private final OrderService orderService;
    @ManagedProperty(value = "cafeMenuService")
    private final CafeMenuServiceImpl cafeMenuService;


    private List<OrderPoint> orderPoints;
    private Boolean acceptOrderForDelivery = false;


    private Integer possiblePrice = 0;
    private Integer fullPrice = 0;
    private Integer gradeId;

    private List<CoffeeGradeViewDTO> enabledItems = new ArrayList<>();
    private Set<CoffeeGradeViewDTO> coffeeGradeView;

    @Value("${inventory.free.cup.number}")
    private Integer freeCupNumber;




    @PostConstruct
    public void init() {
        orderPoints = new ArrayList<>();
    }

    public CartBean(DeliveryBean deliveryBean, OrderService orderService, CafeMenuServiceImpl cafeMenuService) {
        this.deliveryBean = deliveryBean;
        this.orderService = orderService;
        this.cafeMenuService = cafeMenuService;
    }

    public Set<CoffeeGradeViewDTO> getCoffeeGradeView() {

        if(coffeeGradeView == null) {
            coffeeGradeView = new HashSet<>();
            List<CoffeeGrade> coffeeGrades = cafeMenuService.getAllCoffeeGrades();
            for (CoffeeGrade coffeeGrade : coffeeGrades) {
                CoffeeGradeViewDTO coffeeGradeViewDTO = new CoffeeGradeViewDTO();
                BeanUtils.copyProperties(coffeeGrade, coffeeGradeViewDTO);
                coffeeGradeView.add(coffeeGradeViewDTO);
            }
        }
        return coffeeGradeView;
    }


    public void onRowSelectCheckbox(SelectEvent<CoffeeGradeViewDTO> event) {

    }


    public void onRowUnselectCheckbox(SelectEvent<CoffeeGradeViewDTO> event) {

    }

    public void onRowSelect(SelectEvent<CoffeeGradeViewDTO> event) {
        System.out.println("dasfasd");
    }

    public void makeOrder() throws IOException {

        if (orderPoints.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Вы не выбрали напитки!",
                    "Message details");
            context.addMessage(null, message);
            context.validationFailed();
        }

        List<OrderPoint> orderPointList = new ArrayList<>();
        for (CoffeeGradeViewDTO coffeeGradeViewDTO : coffeeGradeView) {
            if (enabledItems.contains(coffeeGradeViewDTO)) {
                orderPointList.add(new OrderPoint(new CoffeeGrade(coffeeGradeViewDTO.getGradeId()), coffeeGradeViewDTO.getCupNumber()));
            }
        }
        deliveryBean.setOrder(orderService.makeOrder(new Order(orderPointList)));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/delivery.jsf");
    }

    public Boolean checkEnable(CoffeeGradeViewDTO item) {
        return enabledItems.contains(item);
    }

    public void calculatePossiblePrice() {
        HashMap<Integer, Integer> selectedItems = new HashMap<>();
        for (CoffeeGradeViewDTO coffeeGradeViewDTO : coffeeGradeView) {
            if (enabledItems.contains(coffeeGradeViewDTO)) {
                selectedItems.put(coffeeGradeViewDTO.getGradeId(), coffeeGradeViewDTO.getCupNumber());
            }
            HashMap<Integer, Integer> possiblePromotion = cafeMenuService.calculatePossiblePrice(selectedItems);
            for (Map.Entry<Integer, Integer> entry : possiblePromotion.entrySet()) {
                possiblePrice = entry.getKey();
                fullPrice = entry.getValue();
            }
        }
    }
}
