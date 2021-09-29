package com.cafe.cafe.service;

import com.cafe.cafe.domain.CoffeeGrade;
import com.cafe.cafe.domain.Order;
import com.cafe.cafe.domain.OrderPoint;
import com.cafe.cafe.exceptions.simpleException.NotFoundException;
import com.cafe.cafe.repository.CoffeeGradeRepo;
import com.cafe.cafe.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final CoffeeGradeRepo coffeeGradeRepo;

    @Value("${inventory.free.cup.number}")
    private Integer freeCupNumber;

    @Value("${inventory.free.delivery.per.price}")
    private Integer freeDeliveryPerPrice;

    @Value("${inventory.default.delivery.price}")
    private Integer defaultDeliveryPrice;


    public OrderServiceImpl(OrderRepo orderRepo, CoffeeGradeRepo coffeeGradeRepo) {
        this.orderRepo = orderRepo;
        this.coffeeGradeRepo = coffeeGradeRepo;
    }

    @Override
    @Transactional
    public Order makeOrder(Order order) {
        order.setFullOrderPrice(calculateOrderPrice(order.getOrderPoints()));
        return orderRepo.save(order);
    }

    @Override
    @Transactional
    public List<Order> getAllOrders() {
        return (List<Order>) orderRepo.findAll();
    }

    private Integer calculateOrderPrice(List<OrderPoint> orderPoints) {
        int fullOrderPrice = 0;

        for (OrderPoint orderPoint: orderPoints) {
            int freeCupCounter = orderPoint.getCupCounter() / freeCupNumber;
            CoffeeGrade coffeeGrade = coffeeGradeRepo.findById(orderPoint.getCoffeeGrade().getGradeId())
                    .orElseThrow(()-> new NotFoundException("{coffe grade not found by Id" + orderPoint.getCoffeeGrade().getGradeId() + "}"));
            fullOrderPrice = (orderPoint.getCupCounter() - freeCupCounter) * coffeeGrade.getPrice();
        }
        if (fullOrderPrice >= freeDeliveryPerPrice) {
            return fullOrderPrice;
        }
        else {
            return fullOrderPrice + defaultDeliveryPrice;
        }
    }


}
