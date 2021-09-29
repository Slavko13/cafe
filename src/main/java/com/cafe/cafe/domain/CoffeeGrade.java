package com.cafe.cafe.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.ManagedBean;
import javax.persistence.*;

@Entity
@Table(name = "coffe_grade")
@Data
@NoArgsConstructor
@ManagedBean

public class CoffeeGrade {

    public CoffeeGrade(Integer gradeId) {
        this.gradeId = gradeId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer gradeId;

    @Column(name = "grade_name")
    private String gradeName;

    private Integer price;

    private Boolean disabled;


}
