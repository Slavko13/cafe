package com.cafe.cafe.dto;

import com.cafe.cafe.domain.OrderPoint;
import com.cafe.cafe.enums.DeliveryType;
import com.cafe.cafe.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String orderId;
    private Date orderDatetime;

    private String customerName;

    private String deliveryAddress;

    private Integer fullOrderPrice;

    private List<OrderPointDTO> orderPoints;

    private OrderStatus status;
    private DeliveryType deliveryType;

    public OrderDTO(List<OrderPointDTO> orderPoints) {
        this.orderPoints = orderPoints;
    }
}
