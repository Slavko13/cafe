package com.cafe.cafe.dto;

import com.cafe.cafe.domain.OrderPoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RowCoffeeGradeDTO {

    private Integer gradeId;
    private String gradeNameRu;
    private String gradeNameEng;
    private Integer price;
    private Boolean disabled;

}
