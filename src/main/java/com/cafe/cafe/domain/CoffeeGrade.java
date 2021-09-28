package com.cafe.cafe.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "coffe_grade")
@Data
@NoArgsConstructor
public class CoffeeGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer gradeId;

    @Column(name = "grade_name")
    private String gradeName;

    private Integer price;

    private Boolean disabled;



}
