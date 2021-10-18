package com.cafe.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
