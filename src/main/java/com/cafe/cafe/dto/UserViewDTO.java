package com.cafe.cafe.dto;


import com.cafe.cafe.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserViewDTO
{

    private Integer userId;
    private String secondName;
    private String firstName;
    private String phoneNumber;
    private String username;
    private List<Order> orderList;



}
