package com.example.carx_test.services;

import com.example.carx_test.models.UserDTO;
import com.example.carx_test.repositories.UserRepositories;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserService {
    private final UserRepositories userRepositories;

    @Autowired
    public UserService(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    public String getJsonUser(String uuid) {

        UUID uuidUser = UUID.fromString(uuid);

        ObjectMapper objectMapper = new ObjectMapper();
        return userRepositories.findUserById(uuidUser).map(user -> {
            try {
                return objectMapper.writeValueAsString(user.getUser_data());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return "";
            }
        }).orElse(null);
    }

    public void setJsonUser(UserDTO data, String uuid) {

        UUID uuidUser = UUID.fromString(uuid);
        userRepositories.findUserById(uuidUser).ifPresent(user -> {
                user.setUser_data(data);
                userRepositories.save(user);
        });
    }
}
