package com.cafe.cafe.repository;


import com.cafe.cafe.domain.order.Order;
import com.cafe.cafe.enums.order.OrderStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepo extends CrudRepository<Order, String>
{

    List<Order> getAllByStatusOrderByOrderDatetimeAsc(OrderStatus orderStatus);


}
