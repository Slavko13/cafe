package com.cafe.cafe.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "order_point")
@Data
@NoArgsConstructor
@ToString(exclude = {"order"})
public class OrderPoint {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer pointId;

    @Column(name = "cup_counter")
    private Integer cupCounter;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;


    @Column(name = "grade_name")
    private String gradeName;
}
