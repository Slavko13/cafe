package com.cafe.cafe.controller;


import com.cafe.cafe.dto.UserViewDTO;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.annotation.ManagedProperty;

@Component
@Scope("session")
@Data
public class UserBean
{

    @ManagedProperty("#{user}")
    private UserViewDTO user;

    public void doLogin() {

    }



}
