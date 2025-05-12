package com.hahn.software.employeerecordsmanagementbackend.security.utils;

import com.hahn.software.employeerecordsmanagementbackend.security.dto.LoginRequest;
import com.hahn.software.employeerecordsmanagementbackend.security.dto.UserResponseDto;
import com.hahn.software.employeerecordsmanagementbackend.security.entity.Role;
import com.hahn.software.employeerecordsmanagementbackend.security.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapper {
    public UsernamePasswordAuthenticationToken mapLoginRequestToAuthenticationUser(LoginRequest request) {
        return new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
    }
    public UserResponseDto mapUserToUserResponseDto(User user, UserResponseDto userResponseDto) {
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setEnabled(user.isEnabled());
        userResponseDto.setRoles(user.getRoles().stream().map(Role::getName).toList());
        userResponseDto.setExpirationDate(user.getExpirationDate());
        userResponseDto.setPasswordExpirationDate(user.getPasswordExpirationDate());
        userResponseDto.setTemporaryPassword(user.isTemporaryPassword());
        return userResponseDto;
    }
    public List<UserResponseDto> mapUsersToUserResponseDtos(List<User> users) {
        return users.stream()
                .map(user -> mapUserToUserResponseDto(user, UserResponseDto.builder().build()))
                .toList();
    }
}
