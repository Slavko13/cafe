package com.cafe.cafe.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "coffee_users")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer userId;

    @Column(name = "secondName")
    private String secondName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Order> orderList;

}
