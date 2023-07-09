package com.example.carx_test.repositories;

import com.example.carx_test.models.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepositories extends JpaRepository<User, UUID> {
    Optional<User> findUserById(UUID id);

//    @Query(value = "SELECT u.user_data FROM users u " +
//            "WHERE u.id IN (" +
//            "SELECT u1.id FROM users u1 WHERE u1.user_data ->> country = u.user_data.country " +
//            "ORDER BY u1.user_data.money DESC LIMIT :limit)")
//    List<String> findUsersWithMaxMoneyByCountry(@Param("limit") int limit);
//


}
