package com.cafe.cafe.controller;


import com.cafe.cafe.domain.CoffeeGrade;
import com.cafe.cafe.domain.Order;
import com.cafe.cafe.service.coffeGrade.CoffeeGradeServiceImpl;
import lombok.Data;
import org.springframework.context.annotation.Scope;

import javax.annotation.ManagedBean;
import javax.faces.annotation.ManagedProperty;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@Scope(value = "session")
@Data
public class CoffeeGradeBean {


    @ManagedProperty("#{coffeeGrade}")
    private CoffeeGrade coffeeGrade;

    private Boolean update = false;
    private Boolean add = false;
    private List<CoffeeGrade> selectedGrades = new ArrayList<>();

    private final CoffeeGradeServiceImpl coffeeGradeService;

    private List<CoffeeGrade> menu;


    public CoffeeGradeBean(CoffeeGradeServiceImpl coffeeGradeService) {
        this.coffeeGradeService = coffeeGradeService;
    }

    public List<CoffeeGrade> getMenu() {
        return coffeeGradeService.getAllCoffeeGrades();
    }

    public void deleteAllHighlighted() {
        coffeeGradeService.deleteCoffeeGrade(selectedGrades);
    }

}
