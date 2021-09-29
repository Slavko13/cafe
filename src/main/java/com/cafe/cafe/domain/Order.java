package com.cafe.cafe.domain;


import com.cafe.cafe.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.annotation.ManagedBean;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor

public class Order {

    public Order(List<OrderPoint> orderPoints) {
        this.orderPoints = orderPoints;
    }

    public Order(UUID orderId) {
        this.orderId = orderId;
    }

    @Id
    @GenericGenerator(name = "id", strategy = "uuid2")
    @Column(name="id")
    private UUID orderId;

    @Column(name = "order_datetime")
    private Date orderDatetime;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "fullOrderPrice")
    private Integer fullOrderPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderPoint> orderPoints;


    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

}
