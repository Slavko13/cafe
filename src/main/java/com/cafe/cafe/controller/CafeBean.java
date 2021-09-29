package com.cafe.cafe.controller;


import com.cafe.cafe.domain.CoffeeGrade;
import com.cafe.cafe.domain.Order;
import com.cafe.cafe.dto.OrderDTO;
import com.cafe.cafe.service.CafeMenuService;
import com.cafe.cafe.service.OrderService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
@Data
public class CafeBean {

    private final CafeMenuService cafeMenuService;

    public CafeBean(CafeMenuService cafeMenuService) {
        this.cafeMenuService = cafeMenuService;
    }

    public List<CoffeeGrade> getCafeMenu() {
        return cafeMenuService.getAllCoffeeGrades();
    }

}
