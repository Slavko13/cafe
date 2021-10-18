package com.cafe.cafe.service;

import com.cafe.cafe.domain.CoffeeGrade;
import com.cafe.cafe.exceptions.simpleException.NotFoundException;
import com.cafe.cafe.repository.CoffeeGradeRepo;
import com.cafe.cafe.service.coffeGrade.CoffeeGradeGetService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CafeMenuServiceImpl implements CafeMenuService, CoffeeGradeGetService
{

    private final CoffeeGradeRepo coffeeGradeRepo;


    @Value("${inventory.free.cup.number}")
    private Integer freeCupNumber;

    public CafeMenuServiceImpl(CoffeeGradeRepo coffeeGradeRepo)
    {
        this.coffeeGradeRepo = coffeeGradeRepo;
    }

    @Override
    @Transactional
    public List<CoffeeGrade> getAllCoffeeGrades()
    {
        return coffeeGradeRepo.getAllByDisabledFalse();
    }


    @Override
    public HashMap<Integer, Integer> calculatePossiblePrice(HashMap<Integer, Integer> selectedItems)
    {
        Integer possiblePrice = 0;
        Integer fullPrice = 0;

        HashMap<Integer, Integer> possiblePricePromotion = new HashMap<>();

        for(Map.Entry<Integer, Integer> entry : selectedItems.entrySet())
        {
            Integer gradeId = entry.getKey();
            Integer cupCounter = entry.getValue();
            int freeCupCounter = cupCounter / freeCupNumber;
            CoffeeGrade coffeeGrade = coffeeGradeRepo.findById(gradeId).orElseThrow(() -> new NotFoundException("{coffe grade not found by Id" + gradeId + "}"));
            possiblePrice += (cupCounter - freeCupCounter) * coffeeGrade.getPrice();
            fullPrice += cupCounter * coffeeGrade.getPrice();
        }
        possiblePricePromotion.put(possiblePrice, fullPrice);
        return possiblePricePromotion;
    }

    @Override
    public CoffeeGrade getById(Integer gradeId)
    {
        return null;
    }
}
