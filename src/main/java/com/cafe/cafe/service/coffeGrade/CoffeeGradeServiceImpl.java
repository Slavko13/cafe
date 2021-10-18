package com.cafe.cafe.service.coffeGrade;

import com.cafe.cafe.domain.CoffeeGrade;
import com.cafe.cafe.domain.OrderPoint;
import com.cafe.cafe.exceptions.simpleException.NotFoundException;
import com.cafe.cafe.repository.CoffeeGradeRepo;
import com.cafe.cafe.repository.OrderPointRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class CoffeeGradeServiceImpl implements CrudCoffeeService, CoffeeGradeGetService
{

    private final CoffeeGradeRepo coffeeGradeRepo;
    private final OrderPointRepo orderPointRepo;

    public CoffeeGradeServiceImpl(CoffeeGradeRepo coffeeGradeRepo, OrderPointRepo orderPointRepo)
    {
        this.coffeeGradeRepo = coffeeGradeRepo;
        this.orderPointRepo = orderPointRepo;
    }

    @Override
    public CoffeeGrade addCoffeeGrade(CoffeeGrade coffeeGrade)
    {
        return coffeeGradeRepo.save(coffeeGrade);
    }

    @Override
    public CoffeeGrade updateCoffeeGrade(CoffeeGrade coffeeGrade)
    {
        if(coffeeGradeRepo.existsById(coffeeGrade.getGradeId()))
        {
            return coffeeGradeRepo.save(coffeeGrade);
        }
        throw new NotFoundException("coffee grade not found");

    }

    @Override
    public CoffeeGrade getById(Integer gradeId)
    {
        return coffeeGradeRepo.findById(gradeId).orElseThrow(() -> new NotFoundException("coffee grade not found"));
    }

    @Override
    public List<CoffeeGrade> getAllCoffeeGrades()
    {
        List<CoffeeGrade> all = (List<CoffeeGrade>) coffeeGradeRepo.findAll();
        return all;
    }

    @Override
    public void deleteCoffeeGrade(List<CoffeeGrade> coffeeGrades)
    {
        coffeeGradeRepo.deleteAll(coffeeGrades);
    }


    @Override
    public List<CoffeeGrade> updateListCoffeeGrade(Collection<CoffeeGrade> coffeeGrades)
    {
        return (List<CoffeeGrade>) coffeeGradeRepo.saveAll(coffeeGrades);
    }
}
