package com.cafe.cafe.domain.user;


import com.cafe.cafe.enums.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_roles")
@Entity
public class UserRole
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer roleId;

    @Enumerated(value = EnumType.STRING)
    private Role role;

}
