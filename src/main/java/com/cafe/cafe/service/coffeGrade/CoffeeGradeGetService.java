package com.cafe.cafe.service.coffeGrade;

import com.cafe.cafe.domain.CoffeeGrade;

import java.util.List;

public interface CoffeeGradeGetService {

    List<CoffeeGrade> getAllCoffeeGrades();
    CoffeeGrade getById(Integer gradeId);

}
