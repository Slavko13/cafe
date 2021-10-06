package com.cafe.cafe.service;

import com.cafe.cafe.domain.CoffeeGrade;

import java.util.HashMap;
import java.util.List;

public interface CafeMenuService {

    List<CoffeeGrade> getAllCoffeeGrades();
    HashMap<Integer, Integer> calculatePossiblePrice(HashMap<Integer, Integer> selectedItems);



}
