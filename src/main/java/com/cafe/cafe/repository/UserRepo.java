package com.cafe.cafe.repository;

import com.cafe.cafe.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Integer>
{
    Optional<User> getUserByUsername(String username);

}
