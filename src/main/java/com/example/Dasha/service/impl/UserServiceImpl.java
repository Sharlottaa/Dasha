package com.example.Dasha.service.impl;

import com.example.Dasha.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import com.example.Dasha.dto.UserDTO;
import com.example.Dasha.entity.User;
import com.example.Dasha.repository.UserRepository;
import com.example.Dasha.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO createUser(String fullName, LocalDate birthDate, String workplace) {
        User user = new User();
        user.setFullName(fullName);
        user.setBirthDate(birthDate);
        user.setWorkplace(workplace);
        user.setMonthlyIncome((int)(Math.random() * 10_000));
        user.setCreditRating((user.getMonthlyIncome() / 1000) * 100);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User repository not found with id " + id));
    }

    @Override
    public UserDTO getUserByIdDto(Long id) {
        return userMapper.toDto(getUserById(id));
    }

    @Override
    public UserDTO updateUser(Long id, String fullName, String workplace) {
        User user = getUserById(id);
        user.setFullName(fullName);
        user.setWorkplace(workplace);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}