package com.example.carx_test.controllers;


import com.example.carx_test.models.User;
import com.example.carx_test.models.UserData;
import com.example.carx_test.repositories.UserRepositories;
import com.example.carx_test.services.UserService;
import com.example.carx_test.services.parsers.Parsing;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserRepositories userRepositories;
    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Проверка на получение данных несуществующего пользователя")
    public void testGetJsonUser_WithOptionalEmpty(){
        UUID uuid = UUID.fromString("bc460371-4469-40e8-a4f1-180706c9ee51");

        when(userRepositories.findUserById(uuid))
                .thenReturn(Optional.empty());
        String result;
        result = userService.getJsonUser(uuid);

        assertNull(result);
        verify(userRepositories).findUserById(uuid);
    }

    @Test
    @DisplayName("Проверка с передачей валидных данных")
    public void testGetJsonUser_WithValidUuid_ReturnsJsonString() throws Exception{
        UUID uuid = UUID.fromString("bc460371-4469-40e8-a4f1-180706c9ee58");
        ZonedDateTime date = new Parsing().parsToZoneDateTime("2022-07-12");
        User user = new User(uuid, new UserData(444, "RU"), date);
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(user.getUser_data());

        when(userRepositories.findUserById(uuid))
                .thenReturn(Optional.of(user));
        String result;
        result = userService.getJsonUser(uuid);

        assertEquals(expectedJson, result);
        verify(userRepositories).findUserById(uuid);
    }

}
