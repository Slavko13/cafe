package com.cafe.cafe.dto;

import com.cafe.cafe.domain.cafe.CoffeeGrade;
import com.cafe.cafe.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPointDTO
{

    public OrderPointDTO(CoffeeGrade coffeeGrade, Integer cupCounter, String gradeName)
    {
        this.coffeeGrade = coffeeGrade;
        this.cupCounter = cupCounter;
        this.gradeName = gradeName;
    }

    private Integer pointId;
    private CoffeeGrade coffeeGrade;
    private Integer cupCounter;
    private Order order;
    private String gradeName;


}
