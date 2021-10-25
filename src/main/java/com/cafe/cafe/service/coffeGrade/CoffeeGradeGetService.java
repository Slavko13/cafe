package com.cafe.cafe.service.coffeGrade;

import com.cafe.cafe.domain.cafe.CoffeeGrade;

import java.util.List;

public interface CoffeeGradeGetService
{

    List<CoffeeGrade> getAllCoffeeGrades();

    CoffeeGrade getById(Integer gradeId);

}
