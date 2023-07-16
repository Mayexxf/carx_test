package com.example.carx_test.services;


import com.example.carx_test.models.Activity;
import com.example.carx_test.models.User;
import com.example.carx_test.repositories.ActivityRepository;
import com.example.carx_test.repositories.AnalyticRepository;
import com.example.carx_test.services.parsers.FastUuidParser;
import com.example.carx_test.services.parsers.Parsing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnalyticService {

    private final Parsing parsing = new Parsing();
    private final AnalyticRepository analyticRepository;
    private final ActivityRepository activityRepository;

    @Autowired
    public AnalyticService(AnalyticRepository analyticRepository, ActivityRepository activityRepository) {
        this.analyticRepository = analyticRepository;
        this.activityRepository = activityRepository;
    }

    public Map<String, List<User>> getUsersWithMaxMoneyByCountry(int limit){
        return analyticRepository.findUsersWithMaxMoneyByCountry(limit).stream()
                .collect(Collectors.groupingBy(User -> User.getUser_data().getCountry()));
    }

    public List<User> countNewUsersByCountry(String starting, String ending){
        ZonedDateTime start = parsing.parsToZoneDateTime(starting);
        ZonedDateTime end = parsing.parsToZoneDateTime(ending);

        return analyticRepository.countNewUsersByCountry(start, end);
    }

    public List<Activity> findUserByDate(String starting, String ending, String uuid){
        ZonedDateTime start = parsing.parsToZoneDateTime(starting);
        ZonedDateTime end = parsing.parsToZoneDateTime(ending);
        UUID uuidUser = FastUuidParser.fromString(uuid);
        System.out.println(uuidUser);

        return activityRepository.UserByDate(start,end,uuidUser);
    }
}
