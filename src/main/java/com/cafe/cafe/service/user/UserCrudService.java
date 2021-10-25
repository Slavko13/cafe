package com.cafe.cafe.service.user;

import com.cafe.cafe.domain.user.User;
import com.cafe.cafe.dto.UserViewDTO;

import java.util.List;

public interface UserCrudService
{

    public User addUser(UserViewDTO userViewDTO);
    public User deleteUser(Integer userId);
    public User getUserById(Integer userId);
    public List<User> getAllUsers();

}
