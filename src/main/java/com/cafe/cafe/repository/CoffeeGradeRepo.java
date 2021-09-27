package com.cafe.cafe.repository;

import com.cafe.cafe.domain.CoffeeGrade;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeGradeRepo extends CrudRepository<CoffeeGrade, Integer> {
}
