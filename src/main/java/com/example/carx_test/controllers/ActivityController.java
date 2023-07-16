package com.example.carx_test.controllers;

import com.example.carx_test.services.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> receptSyncActiv(@RequestParam int activity, @RequestParam UUID uuid){
        activityService.setActivityUser(activity, uuid);
        return ResponseEntity.ok("Data received successfully");
    }
}
