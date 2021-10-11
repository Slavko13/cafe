package com.cafe.cafe.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.annotation.ManagedBean;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "coffe_grade")
@Data
@NoArgsConstructor
@ToString( exclude = "orderPointList")
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

    @OneToMany(mappedBy = "coffeeGrade", orphanRemoval = true, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<OrderPoint> orderPointList;

    private Integer price;

    private Boolean disabled;


}
