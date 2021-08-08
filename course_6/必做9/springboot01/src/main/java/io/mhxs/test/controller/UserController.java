package io.mhxs.test.controller;


import io.mhxs.test.entity.UserEntity;
import io.mhxs.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@Validated
@RequestMapping("/api/v1/user")
public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping("/{userId}")
    public UserEntity get(@PathVariable(name = "userId") Long userId) throws Exception {
        return userService.getbyId(userId);
    }

    @GetMapping("/add")
    public ResponseEntity insert() throws Exception {
        userService.insert();
        return ResponseEntity.ok().build();
    }

}
