package com.cafe.cafe.repository;

import com.cafe.cafe.domain.order.OrderPoint;
import org.springframework.data.repository.CrudRepository;

public interface OrderPointRepo extends CrudRepository<OrderPoint, Integer>
{


}
