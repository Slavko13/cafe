package com.cafe.cafe.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_point")
@Data
@NoArgsConstructor
public class OrderPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer pointId;

    @Column(name = "cup_counter")
    private Integer cupCounter;




}
