package com.cafe.cafe.repository;

import com.cafe.cafe.domain.Order;
import com.cafe.cafe.domain.OrderPoint;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderPointRepo extends CrudRepository<OrderPoint, Integer> {


}
