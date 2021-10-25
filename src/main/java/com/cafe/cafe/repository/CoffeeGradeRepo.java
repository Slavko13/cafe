package com.cafe.cafe.repository;


import com.cafe.cafe.domain.cafe.CoffeeGrade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoffeeGradeRepo extends CrudRepository<CoffeeGrade, Integer>
{

    List<CoffeeGrade> getAllByDisabledFalse();


}
