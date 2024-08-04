package com.breaksloop.panjayathu.controller;

import com.breaksloop.panjayathu.common.LMResponse;
import com.breaksloop.panjayathu.entity.User;
import com.breaksloop.panjayathu.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/new")
    public LMResponse createNewUser(@RequestBody User user){
        return userService.createNewUser(user);
    }

}
