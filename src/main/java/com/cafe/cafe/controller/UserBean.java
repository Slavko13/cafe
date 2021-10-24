package com.cafe.cafe.controller;


import com.cafe.cafe.domain.Order;
import com.cafe.cafe.domain.User;
import com.cafe.cafe.dto.UserViewDTO;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;

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
