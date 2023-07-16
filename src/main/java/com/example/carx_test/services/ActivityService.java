package com.example.carx_test.services;

import com.example.carx_test.models.Activity;
import com.example.carx_test.repositories.ActivityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@Transactional
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public void setActivityUser (int activityUser, UUID user_id){

        Activity newActivityUser = new Activity();
        activityRepository.findByUser(user_id)
                .findFirst().
                ifPresent(activity -> {
            newActivityUser.setUser(user_id);
            newActivityUser.setActivity(activityUser);
            newActivityUser.setActivityDate(ZonedDateTime.now());
        });
        activityRepository.save(newActivityUser);
    }
}
