package com.go2it.springboot.repository;

import com.go2it.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
}
