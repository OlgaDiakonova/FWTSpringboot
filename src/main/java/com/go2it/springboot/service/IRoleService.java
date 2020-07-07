package com.go2it.springboot.service;

import com.go2it.springboot.entity.Role;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findById(int id);
    void save(Role role);
}
