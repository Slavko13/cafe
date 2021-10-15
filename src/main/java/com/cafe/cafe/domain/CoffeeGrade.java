package com.cafe.cafe.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.annotation.ManagedBean;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    private Integer price;

    private Boolean disabled;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoffeeGrade that = (CoffeeGrade) o;
        return Objects.equals(gradeId, that.gradeId) && Objects.equals(gradeNameRu, that.gradeNameRu) && Objects.equals(gradeNameEng, that.gradeNameEng) && Objects.equals(price, that.price) && Objects.equals(disabled, that.disabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gradeId, gradeNameRu, gradeNameEng, price, disabled);
    }
}
