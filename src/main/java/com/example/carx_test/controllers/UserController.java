package com.example.carx_test.controllers;

import com.example.carx_test.models.UserData;
import com.example.carx_test.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<String> responceSyncData(@RequestParam UUID uuid){
            return ResponseEntity.ok(userService.getJsonUser(uuid));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> receivingSyncData (@RequestBody UserData data, @RequestParam String uuid){

        userService.setJsonUser(data, uuid);
        return ResponseEntity.ok("Data received successfully");
    }
}
