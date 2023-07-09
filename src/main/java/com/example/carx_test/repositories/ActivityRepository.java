package com.example.carx_test.repositories;

import com.example.carx_test.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Stream<Object[]> findByUser (UUID uuid);
}
