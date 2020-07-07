package com.go2it.springboot.service;

import com.go2it.springboot.entity.User;
import com.go2it.springboot.entity.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> findById(int id);
    void save(User user);
    void save(UserDTO userDto);
    List<User> findAll();
}
