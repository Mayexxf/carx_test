package com.example.carx_test.controllers;

import com.example.carx_test.models.UserDTO;
import com.example.carx_test.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public String responceSyncData(String uuid){
        return userService.getJsonUser(uuid);
    }

    @PostMapping
    public ResponseEntity<String> receivingSyncData (@RequestBody UserDTO data, @RequestParam String uuid){

        userService.setJsonUser(data, uuid);
        return ResponseEntity.ok("Data received successfully");
    }

}
