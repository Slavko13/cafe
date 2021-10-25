package com.cafe.cafe.service.user;

import com.cafe.cafe.domain.user.User;
import com.cafe.cafe.dto.UserViewDTO;
import com.cafe.cafe.exceptions.simpleException.NotFoundException;
import com.cafe.cafe.repository.UserRepo;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class UserService implements UserCrudService
{
    private final UserRepo userRepo;

    public UserService(final UserRepo userRepo)
    {
        this.userRepo = userRepo;
    }

    @Override
    public User addUser(final UserViewDTO userViewDTO)
    {
        User newUser = new User();
        BeanUtils.copyProperties(userViewDTO, newUser);
        return userRepo.save(newUser);
    }

    @Override
    public User deleteUser(final Integer userId)
    {
        return null;
    }

    @Override
    public User getUserByUsername(final String username)
    {
        return userRepo.getUserByUsername(username).orElseThrow(()-> new NotFoundException("User not found by username: " + username));
    }

    @Override
    public List<User> getAllUsers()
    {
        return (List<User>) userRepo.findAll();
    }
}
