package com.cafe.cafe.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.ManagedBean;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer orderId;

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


}
