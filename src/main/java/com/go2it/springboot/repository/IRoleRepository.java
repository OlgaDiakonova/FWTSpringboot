package com.go2it.springboot.repository;

import com.go2it.springboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Integer> {

}
