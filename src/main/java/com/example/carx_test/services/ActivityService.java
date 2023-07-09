package com.example.carx_test.services;

import com.example.carx_test.models.Activity;
import com.example.carx_test.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@Transactional
public class ActivityService {

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public void setActivityUser (int activityUser, String user_id){
        UUID uuid = UUID.fromString(user_id);

        Activity newActivityUser = new Activity();
        activityRepository.findByUser(uuid)
                .findFirst()
                .ifPresent(activity -> {
            newActivityUser.setUser(uuid);
            newActivityUser.setActivity(activityUser);
            newActivityUser.setActivity_date(ZonedDateTime.now());
        });
        activityRepository.save(newActivityUser);
    }
}
