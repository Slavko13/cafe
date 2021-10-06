package com.cafe.cafe.service;

import com.cafe.cafe.domain.CoffeeGrade;
import com.cafe.cafe.domain.Order;
import com.cafe.cafe.domain.OrderPoint;
import com.cafe.cafe.enums.DeliveryType;
import com.cafe.cafe.enums.OrderStatus;
import com.cafe.cafe.exceptions.simpleException.NotFoundException;
import com.cafe.cafe.repository.CoffeeGradeRepo;
import com.cafe.cafe.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.UUID;

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
        order.setOrderId(createUniqueOrderNumber());
        order.setFullOrderPrice(calculateOrderPriceWithoutDelivery(order));
        order.setStatus(OrderStatus.ACTIVE);
        return orderRepo.save(order);
    }

    @Override
    @Transactional
    public List<Order> getAllOrdersByStatus(String orderStatus) {
        return orderRepo.getAllByStatusOrderByOrderDatetimeAsc(OrderStatus.valueOf(orderStatus));
    }


    @Override
    @Transactional
    public Order confirmOrderByUser(Order order) {
        order.setStatus(OrderStatus.CONFIRMED);
        if (order.getDeliveryType().equals(DeliveryType.PICKUP)) {
            return orderRepo.save(order);
        }
        else if (order.getFullOrderPrice() > freeDeliveryPerPrice) {
            return orderRepo.save(order);
        }
        else {
            order.setFullOrderPrice(order.getFullOrderPrice() + defaultDeliveryPrice);
            return orderRepo.save(order);
        }
    }

    @Override
    public Order changeOrderStatus(UUID orderId, OrderStatus orderStatus) {
        Order order = orderRepo.findById(orderId).orElseThrow(()-> new NotFoundException("{order not found with id " + orderId + "}"));
        order.setStatus(orderStatus);
        return orderRepo.save(order);
    }


    private String createUniqueOrderNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'a');
        stringBuilder.append(c).append('-').append(System.currentTimeMillis());
        return stringBuilder.toString();
    }


    private Integer calculateOrderPriceWithoutDelivery(Order order) {
        int fullOrderPrice = 0;

        for (OrderPoint orderPoint: order.getOrderPoints()) {
            int freeCupCounter = orderPoint.getCupCounter() / freeCupNumber;
            orderPoint.setOrder(new Order(order.getOrderId()));
            CoffeeGrade coffeeGrade = coffeeGradeRepo.findById(orderPoint.getCoffeeGrade().getGradeId())
                    .orElseThrow(()-> new NotFoundException("{coffe grade not found by Id" + orderPoint.getCoffeeGrade().getGradeId() + "}"));
            fullOrderPrice += (orderPoint.getCupCounter() - freeCupCounter) * coffeeGrade.getPrice();
        }
        return fullOrderPrice;
    }
}
