package com.hahn.software.employeerecordsmanagementbackend.security.service;


import com.hahn.software.employeerecordsmanagementbackend.security.dto.UserRegisterDto;
import com.hahn.software.employeerecordsmanagementbackend.security.dto.UserResponseDto;

import java.util.List;

public interface IUserService {
    UserResponseDto registerUser(UserRegisterDto userDto);

    UserResponseDto updateUser(String name, UserRegisterDto userDto);

    List<UserResponseDto> getAllUserProfiles();
    void deleteUser(String name);
}
