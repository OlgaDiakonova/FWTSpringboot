package com.go2it.springboot.service;

import com.go2it.springboot.entity.User;
import com.go2it.springboot.entity.dto.UserDTO;
import com.go2it.springboot.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;


    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void save(UserDTO userDto) {
        User user = new User();
        user.setFirst_name(userDto.getFirst_name());
        user.setLast_name(userDto.getLast_name());
        save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
