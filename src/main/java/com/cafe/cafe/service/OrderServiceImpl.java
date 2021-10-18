package com.cafe.cafe.service;

import com.cafe.cafe.domain.CoffeeGrade;
import com.cafe.cafe.domain.Order;
import com.cafe.cafe.domain.OrderPoint;
import com.cafe.cafe.dto.OrderDTO;
import com.cafe.cafe.dto.OrderPointDTO;
import com.cafe.cafe.enums.DeliveryType;
import com.cafe.cafe.enums.OrderStatus;
import com.cafe.cafe.exceptions.simpleException.NotFoundException;
import com.cafe.cafe.repository.CoffeeGradeRepo;
import com.cafe.cafe.repository.OrderRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService
{

    private final OrderRepo orderRepo;
    private final CoffeeGradeRepo coffeeGradeRepo;

    @Value("${inventory.free.cup.number}")
    private Integer freeCupNumber;

    @Value("${inventory.free.delivery.per.price}")
    private Integer freeDeliveryPerPrice;

    @Value("${inventory.default.delivery.price}")
    private Integer defaultDeliveryPrice;


    public OrderServiceImpl(OrderRepo orderRepo, CoffeeGradeRepo coffeeGradeRepo)
    {
        this.orderRepo = orderRepo;
        this.coffeeGradeRepo = coffeeGradeRepo;
    }

    @Override
    @Transactional
    public Order makeOrder(OrderDTO orderDTO)
    {
        orderDTO.setOrderId(createUniqueOrderNumber());
        orderDTO.setFullOrderPrice(calculateOrderPriceWithoutDelivery(orderDTO));
        orderDTO.setStatus(OrderStatus.ACTIVE);
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        List<OrderPoint> orderPoints = new ArrayList<>();
        for(OrderPointDTO orderPointDTO : orderDTO.getOrderPoints())
        {
            OrderPoint orderPoint = new OrderPoint();
            BeanUtils.copyProperties(orderPointDTO, orderPoint);
            orderPoints.add(orderPoint);
        }
        order.setOrderPoints(orderPoints);
        return orderRepo.save(order);
    }

    @Override
    @Transactional
    public List<Order> getAllOrdersByStatus(String orderStatus)
    {
        return orderRepo.getAllByStatusOrderByOrderDatetimeAsc(OrderStatus.valueOf(orderStatus));
    }


    @Override
    @Transactional
    public Order confirmOrderByUser(Order order)
    {
        order.setStatus(OrderStatus.CONFIRMED);
        if(order.getDeliveryType().equals(DeliveryType.PICKUP))
        {
            return orderRepo.save(order);
        }
        else if(order.getFullOrderPrice() > freeDeliveryPerPrice)
        {
            return orderRepo.save(order);
        }
        else
        {
            order.setFullOrderPrice(order.getFullOrderPrice() + defaultDeliveryPrice);
            return orderRepo.save(order);
        }
    }

    @Override
    public Order changeOrderStatus(String orderId, OrderStatus orderStatus)
    {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new NotFoundException("{order not found with id " + orderId + "}"));
        order.setStatus(orderStatus);
        return orderRepo.save(order);
    }


    private String createUniqueOrderNumber()
    {
        StringBuilder stringBuilder = new StringBuilder();
        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'a');
        stringBuilder.append(c).append('-').append((int) (Math.random() * (900 + 1)) - 1);
        return stringBuilder.toString();
    }


    private Integer calculateOrderPriceWithoutDelivery(OrderDTO orderDTO)
    {
        int fullOrderPrice = 0;

        for(OrderPointDTO orderPoint : orderDTO.getOrderPoints())
        {
            int freeCupCounter = orderPoint.getCupCounter() / freeCupNumber;
            orderPoint.setOrder(new Order(orderDTO.getOrderId()));
            CoffeeGrade coffeeGrade = coffeeGradeRepo.findById(orderPoint.getCoffeeGrade().getGradeId()).orElseThrow(() -> new NotFoundException("{coffe grade not found by Id" + orderPoint.getCoffeeGrade().getGradeId() + "}"));
            fullOrderPrice += (orderPoint.getCupCounter() - freeCupCounter) * coffeeGrade.getPrice();
        }
        return fullOrderPrice;
    }
}
