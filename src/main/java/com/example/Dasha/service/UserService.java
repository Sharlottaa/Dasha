package com.example.Dasha.service;

import com.example.Dasha.dto.UserDTO;
import com.example.Dasha.entity.User;

import java.time.LocalDate;

public interface UserService {
    UserDTO createUser(String fullName, LocalDate birthDate, String workplace);

    User getUserById(Long id);

    UserDTO getUserByIdDto(Long id);

    UserDTO updateUser(Long id, String fullName, String workplace);

    void deleteUser(Long id);
}