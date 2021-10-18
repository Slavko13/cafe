package com.cafe.cafe.dto;

import com.cafe.cafe.domain.OrderPoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeGradeViewDTO
{

    private Integer gradeId;

    private String gradeNameRu;

    private Integer cupNumber;

    private String gradeNameEng;

    private Integer price;

    private Boolean disabled;


    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(o == null || getClass() != o.getClass())
        {
            return false;
        }
        CoffeeGradeViewDTO that = (CoffeeGradeViewDTO) o;
        return Objects.equals(gradeId, that.gradeId) && Objects.equals(gradeNameRu, that.gradeNameRu) && Objects.equals(gradeNameEng, that.gradeNameEng) && Objects.equals(price, that.price) && Objects.equals(disabled, that.disabled);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(gradeId, gradeNameRu, gradeNameEng, price, disabled);
    }
}
