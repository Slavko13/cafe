package com.cafe.cafe.service.coffeGrade;

import com.cafe.cafe.domain.CoffeeGrade;

import java.util.List;

public interface CrudCoffeeService {

    CoffeeGrade addCoffeeGrade(CoffeeGrade coffeeGrade);
    CoffeeGrade updateCoffeeGrade(CoffeeGrade coffeeGrade);
    void deleteCoffeeGrade(List<CoffeeGrade> coffeeGrades);

}
