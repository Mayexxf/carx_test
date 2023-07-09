package com.example.carx_test.repositories;

import com.example.carx_test.models.Activity;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ActivityRepository extends JpaAttributeConverter<Activity, Long> {
    Optional<Activity> findUser_idByActivity(UUID uuid);
}
