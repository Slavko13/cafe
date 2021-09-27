package com.cafe.cafe.repository;

import com.cafe.cafe.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, Integer> {
}
