package com.cafe.cafe.service;

import com.cafe.cafe.domain.CoffeeGrade;
import com.cafe.cafe.repository.CoffeeGradeRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CafeMenuServiceImpl implements CafeMenuService {

    private final CoffeeGradeRepo coffeeGradeRepo;

    public CafeMenuServiceImpl(CoffeeGradeRepo coffeeGradeRepo) {
        this.coffeeGradeRepo = coffeeGradeRepo;
    }

    @Override
    @Transactional
    public List<CoffeeGrade> getAllCoffeeGrades() {
        return  coffeeGradeRepo.getAllByDisabledFalse();
    }
}
