package com.go2it.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.go2it.springboot.entity.Role;
import com.go2it.springboot.entity.User;
import com.go2it.springboot.service.IRoleService;
import com.go2it.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody String userJson) throws IOException {

        if (userJson == null || userJson.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        User customer = objectMapper.readValue(userJson, User.class);
        if (customer != null) {
            roleService.findById(1).ifPresent(role -> {
                customer.setRole(role);
            });
            userService.save(customer);

            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
