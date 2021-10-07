package com.cafe.cafe.service;

import com.cafe.cafe.domain.CoffeeGrade;
import com.cafe.cafe.service.coffeGrade.CoffeeGradeGetService;


import java.util.HashMap;
import java.util.List;

public interface CafeMenuService {


    HashMap<Integer, Integer> calculatePossiblePrice(HashMap<Integer, Integer> selectedItems);



}
