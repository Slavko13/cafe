package com.cafe.cafe.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.ManagedBean;
import javax.persistence.*;

@Entity
@Table(name = "order_point")
@Data
@NoArgsConstructor
@ToString(exclude = "order")
public class OrderPoint {

    public OrderPoint(CoffeeGrade coffeeGrade, Integer cupCounter) {
        this.coffeeGrade = coffeeGrade;
        this.cupCounter = cupCounter;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer pointId;

    @OneToOne
    @JoinColumn(name = "coffee_grade_id")
    private CoffeeGrade coffeeGrade;

    @Column(name = "cup_counter")
    private Integer cupCounter;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;

}
