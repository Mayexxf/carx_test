package com.example.carx_test.repositories;


import com.example.carx_test.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AnalyticRepository extends JpaRepository<User, UUID> {

    @Query(value = "SELECT *\n" +
            "FROM (\n" +
            "  SELECT *,\n" +
            "    ROW_NUMBER() OVER (PARTITION BY user_data ->> 'country' ORDER BY CAST(user_data ->> 'money' As numeric) DESC) AS rn\n" +
            "  FROM carx_test.public.users\n" +
            ") AS subquery\n" +
            "WHERE rn <= :limit ", nativeQuery = true)
    List<User> findUsersWithMaxMoneyByCountry(@Param("limit") int limit);


    @Query(value = "SELECT *\n" +
            "FROM users u\n" +
            "WHERE u.date_create >= :startDate\n" +
            "  AND u.date_create <= :endDate\n" +
            "GROUP BY u.user_data ->> 'country', u.date_create, id", nativeQuery = true)
    List<User> countNewUsersByCountry(@Param("startDate") ZonedDateTime startDate, @Param("endDate") ZonedDateTime endDate);

}
