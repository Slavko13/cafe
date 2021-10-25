package com.cafe.cafe.service.coffeGrade;

import com.cafe.cafe.domain.cafe.CoffeeGrade;

import java.util.Collection;
import java.util.List;

public interface CrudCoffeeService
{

    CoffeeGrade addCoffeeGrade(CoffeeGrade coffeeGrade);

    CoffeeGrade updateCoffeeGrade(CoffeeGrade coffeeGrade);

    void deleteCoffeeGrade(List<CoffeeGrade> coffeeGrades);

    List<CoffeeGrade> updateListCoffeeGrade(Collection<CoffeeGrade> coffeeGrades);

}
