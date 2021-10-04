package com.cafe.cafe.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.ManagedBean;
import javax.persistence.*;

@Entity
@Table(name = "coffe_grade")
@Data
@NoArgsConstructor
public class CoffeeGrade {

    public CoffeeGrade(Integer gradeId) {
        this.gradeId = gradeId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer gradeId;

    @Column(name = "grade_name_ru")
    private String gradeNameRu;

    @Column(name = "grade_name_eng")
    private String gradeNameEng;


    private Integer price;

    private Boolean disabled;


}
