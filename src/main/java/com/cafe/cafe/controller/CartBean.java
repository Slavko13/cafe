package com.cafe.cafe.controller;


import com.cafe.cafe.domain.CoffeeGrade;
import com.cafe.cafe.dto.CoffeeGradeViewDTO;
import com.cafe.cafe.dto.OrderDTO;
import com.cafe.cafe.dto.OrderPointDTO;
import com.cafe.cafe.service.CafeMenuServiceImpl;
import com.cafe.cafe.service.OrderService;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.IOException;
import java.util.*;

@Component
@ViewScoped
public class CartBean
{

    @ManagedProperty(value = "deliveryBean")
    private final DeliveryBean deliveryBean;
    @ManagedProperty(value = "orderService")
    private final OrderService orderService;
    @ManagedProperty(value = "cafeMenuService")
    private final CafeMenuServiceImpl cafeMenuService;

    private Boolean chosenDrinksValidation = false;


    private List<OrderPointDTO> orderPoints;
    private Boolean acceptOrderForDelivery = false;


    private Integer possiblePrice = 0;
    private Integer fullPrice = 0;
    private Integer gradeId;

    private Boolean renderMainContainer = true;

    private List<CoffeeGradeViewDTO> enabledItems = new ArrayList<>();
    private Set<CoffeeGradeViewDTO> coffeeGradeView = new HashSet<>();

    @Value("${inventory.free.cup.number}")
    private Integer freeCupNumber;


    @PostConstruct
    public void init()
    {
        orderPoints = new ArrayList<>();
    }

    public CartBean(DeliveryBean deliveryBean, OrderService orderService, CafeMenuServiceImpl cafeMenuService)
    {
        this.deliveryBean = deliveryBean;
        this.orderService = orderService;
        this.cafeMenuService = cafeMenuService;
    }

    public Set<CoffeeGradeViewDTO> getCoffeeGradeView()
    {
        return coffeeGradeView;
    }


    public void onRowSelectCheckbox(SelectEvent<CoffeeGradeViewDTO> event)
    {
    }


    public void onRowUnselectCheckbox(UnselectEvent<CoffeeGradeViewDTO> event)
    {

    }

    public void onRowSelect(SelectEvent<CoffeeGradeViewDTO> event)
    {
    }

    public void makeOrder() throws IOException
    {
        if(possiblePrice > 0)
        {
            if(orderPoints.isEmpty() && enabledItems.isEmpty())
            {
                chosenDrinksValidation = true;
            }
            else
            {

                List<OrderPointDTO> orderPointList = new ArrayList<>();
                for(CoffeeGradeViewDTO coffeeGradeViewDTO : coffeeGradeView)
                {
                    if(enabledItems.contains(coffeeGradeViewDTO))
                    {
                        orderPointList.add(new OrderPointDTO(new CoffeeGrade(coffeeGradeViewDTO.getGradeId()), coffeeGradeViewDTO.getCupNumber(), coffeeGradeViewDTO.getGradeNameRu()));
                    }
                }
                deliveryBean.setOrder(orderService.makeOrder(new OrderDTO(orderPointList)));
                chosenDrinksValidation = false;
                possiblePrice = 0;
                fullPrice = 0;
                FacesContext.getCurrentInstance().getExternalContext().redirect("/delivery.jsf");
            }
        }
        else {
            chosenDrinksValidation = true;
        }
    }

    public Boolean checkEnable(CoffeeGradeViewDTO item)
    {
        return enabledItems.contains(item);
    }


    public void calculatePossiblePrice()
    {
        HashMap<Integer, Integer> selectedItems = new HashMap<>();
        for(CoffeeGradeViewDTO coffeeGradeViewDTO : coffeeGradeView)
        {
            if(enabledItems.contains(coffeeGradeViewDTO))
            {
                selectedItems.put(coffeeGradeViewDTO.getGradeId(), coffeeGradeViewDTO.getCupNumber());
            }
            HashMap<Integer, Integer> possiblePromotion = cafeMenuService.calculatePossiblePrice(selectedItems);
            for(Map.Entry<Integer, Integer> entry : possiblePromotion.entrySet())
            {
                if(entry.getKey() >= 0)
                {
                    possiblePrice = entry.getKey();
                }
                if(entry.getValue() >= 0)
                {
                    fullPrice = entry.getValue();
                }
            }
        }
    }


    public void onload()
    {
        coffeeGradeView = new HashSet<>();
        List<CoffeeGrade> coffeeGrades = cafeMenuService.getAllCoffeeGrades();
        for(CoffeeGrade coffeeGrade : coffeeGrades)
        {
            CoffeeGradeViewDTO coffeeGradeViewDTO = new CoffeeGradeViewDTO();
            BeanUtils.copyProperties(coffeeGrade, coffeeGradeViewDTO);
            coffeeGradeView.add(coffeeGradeViewDTO);
        }
        renderMainContainer = !coffeeGradeView.isEmpty();
    }


    public Boolean getAcceptOrderForDelivery()
    {
        return acceptOrderForDelivery;
    }

    public void setAcceptOrderForDelivery(Boolean acceptOrderForDelivery)
    {
        this.acceptOrderForDelivery = acceptOrderForDelivery;
    }

    public Integer getPossiblePrice()
    {
        return possiblePrice;
    }

    public void setPossiblePrice(Integer possiblePrice)
    {
        this.possiblePrice = possiblePrice;
    }

    public Integer getFullPrice()
    {
        return fullPrice;
    }

    public void setFullPrice(Integer fullPrice)
    {
        this.fullPrice = fullPrice;
    }

    public Integer getGradeId()
    {
        return gradeId;
    }

    public void setGradeId(Integer gradeId)
    {
        this.gradeId = gradeId;
    }

    public List<CoffeeGradeViewDTO> getEnabledItems()
    {
        return enabledItems;
    }

    public void setEnabledItems(List<CoffeeGradeViewDTO> enabledItems)
    {
        this.enabledItems = enabledItems;
    }

    public void setCoffeeGradeView(Set<CoffeeGradeViewDTO> coffeeGradeView)
    {
        this.coffeeGradeView = coffeeGradeView;
    }

    public Integer getFreeCupNumber()
    {
        return freeCupNumber;
    }

    public void setFreeCupNumber(Integer freeCupNumber)
    {
        this.freeCupNumber = freeCupNumber;
    }

    public Boolean getChosenDrinksValidation()
    {
        return chosenDrinksValidation;
    }

    public void setChosenDrinksValidation(Boolean chosenDrinksValidation)
    {
        this.chosenDrinksValidation = chosenDrinksValidation;
    }

    public Boolean getRenderMainContainer()
    {
        return renderMainContainer;
    }

    public void setRenderMainContainer(final Boolean renderMainContainer)
    {
        this.renderMainContainer = renderMainContainer;
    }
}
