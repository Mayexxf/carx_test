package com.example.carx_test.controllers;


import com.example.carx_test.models.Activity;
import com.example.carx_test.models.User;
import com.example.carx_test.services.AnalyticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/analytic")
public class AnalyticController {

    private final AnalyticService analyticService;

    @Autowired
    public AnalyticController(AnalyticService analyticService) {
        this.analyticService = analyticService;
    }

    @GetMapping("/getUsersWhichMaxMoney")
    @ResponseBody
    public Map<String, List<User>> findUsersWithMaxMoneyByCountry(@RequestParam("limit") int limit){
        return analyticService.getUsersWithMaxMoneyByCountry(limit);
    }

    @GetMapping("/getNewUsers")
    @ResponseBody
    public List<User> countNewUsersByCountry(@RequestParam("start") String  start, @RequestParam("end")String  end){
        return analyticService.countNewUsersByCountry(start, end);
    }

    @GetMapping("/getActivityByUser")
    @ResponseBody
    public List<Activity> findUserByDate_createBeforeAndDate_createBefore(@RequestParam("start") String start, @RequestParam("end")String end, @RequestParam("user") String user){
        return analyticService.findUserByDate(start, end, user);
    }
}
