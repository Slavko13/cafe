package com.cafe.cafe.service.coffeGrade;

import com.cafe.cafe.domain.CoffeeGrade;
import com.cafe.cafe.exceptions.simpleException.NotFoundException;
import com.cafe.cafe.repository.CoffeeGradeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeGradeServiceImpl implements CrudCoffeeService, CoffeeGradeGetService {

    private final CoffeeGradeRepo coffeeGradeRepo;

    public CoffeeGradeServiceImpl(CoffeeGradeRepo coffeeGradeRepo) {
        this.coffeeGradeRepo = coffeeGradeRepo;
    }

    @Override
    public CoffeeGrade addCoffeeGrade(CoffeeGrade coffeeGrade) {
        return coffeeGradeRepo.save(coffeeGrade);
    }

    @Override
    public CoffeeGrade updateCoffeeGrade(CoffeeGrade coffeeGrade) {
        if (coffeeGradeRepo.existsById(coffeeGrade.getGradeId())) {
            return coffeeGradeRepo.save(coffeeGrade);
        }
        throw new NotFoundException("coffee grade not found");

    }

    @Override
    public CoffeeGrade getById(Integer gradeId) {
        return coffeeGradeRepo.findById(gradeId).orElseThrow(()-> new NotFoundException("coffee grade not found"));
    }

    @Override
    public List<CoffeeGrade> getAllCoffeeGrades() {
        return (List<CoffeeGrade>) coffeeGradeRepo.findAll();
    }

    @Override
    public void deleteCoffeeGrade(List<CoffeeGrade> coffeeGrades) {
        coffeeGradeRepo.deleteAll(coffeeGrades);
    }
}
