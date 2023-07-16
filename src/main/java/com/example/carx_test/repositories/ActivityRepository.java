package com.example.carx_test.repositories;

import com.example.carx_test.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Stream<Activity> findByUser(UUID uuid);

    @Query(value = "select *\n" +
            "from activity a\n" +
            "WHERE a.user_id = :userId " +
            "  AND a.activity_date >= :startDate\n" +
            "  AND a.activity_date <= :endDate\n" +
            "GROUP BY a.activity_date, a.user_id, a.activity, id;", nativeQuery = true)
    List<Activity> UserByDate(@Param("startDate") ZonedDateTime start, @Param("endDate")ZonedDateTime end, @Param("userId") UUID userId);
}
