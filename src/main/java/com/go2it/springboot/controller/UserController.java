package com.go2it.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.go2it.springboot.entity.Role;
import com.go2it.springboot.entity.User;
import com.go2it.springboot.entity.dto.UserDTO;
import com.go2it.springboot.service.IRoleService;
import com.go2it.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
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

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String showUserLogin(@PathVariable String id) {
        UserDTO userDto = new UserDTO();
        String userFullName = "";
        userService.findById(Integer.valueOf(id)).ifPresent(user -> {
            userDto.setFirst_name(user.getFirst_name());
            userDto.setLast_name(user.getLast_name());
        });

        if(userDto.getFirst_name() == null && userDto.getLast_name() == null){
            userFullName = "Unknown User";
        }else {
            userFullName = userDto.getFirst_name() + " " + userDto.getLast_name();
        }
        String htmlText = "<div style=\"text-align:center;\">" + "<h1>You are logged in as " + userFullName + "</h1>" + "</div>";

        return htmlText;

    }
}
