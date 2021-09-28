package com.cafe.cafe.controller;


import com.cafe.cafe.domain.CoffeeGrade;
import com.cafe.cafe.service.CafeMenuService;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
public class CafeController {

    private final CafeMenuService cafeMenuService;

    public CafeController(CafeMenuService cafeMenuService) {
        this.cafeMenuService = cafeMenuService;
    }

    public String getTest() {
        return "coffee";
    }



    public List<CoffeeGrade> getCafeMenu() {
        return cafeMenuService.getAllCoffeeGrades();
    }




}
