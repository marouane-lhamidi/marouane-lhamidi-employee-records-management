package com.hahn.software.employeerecordsmanagementbackend.security.service.impl;

import com.hahn.software.employeerecordsmanagementbackend.security.dto.UserRegisterDto;
import com.hahn.software.employeerecordsmanagementbackend.security.dto.UserResponseDto;
import com.hahn.software.employeerecordsmanagementbackend.security.entity.Role;
import com.hahn.software.employeerecordsmanagementbackend.security.entity.User;
import com.hahn.software.employeerecordsmanagementbackend.security.exception.SecurityResourceNotFoundException;
import com.hahn.software.employeerecordsmanagementbackend.security.exception.UserAlreadyExistsException;
import com.hahn.software.employeerecordsmanagementbackend.security.repository.RoleRepository;
import com.hahn.software.employeerecordsmanagementbackend.security.repository.UserRepository;
import com.hahn.software.employeerecordsmanagementbackend.security.service.IUserService;
import com.hahn.software.employeerecordsmanagementbackend.security.utils.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service @AllArgsConstructor @Transactional
public class IUserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Override
    public UserResponseDto registerUser(UserRegisterDto userDto) {
        if (userRepository.getUserByUsername(userDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists with username " + userDto.getUsername());
        }
        if (userRepository.getUserByEmail(userDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists with email " + userDto.getEmail());
        }
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        List<Role> userRoles = roleRepository.getRolesByNames(userDto.getRoles());

        Timestamp expirationDate = new Timestamp(System.currentTimeMillis() + (userDto.getExpirationDuration() * 24 * 60 * 60 * 1000));

        User user = User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(encodedPassword)
                .enabled(userDto.isEnabled())
                .isTemporaryPassword(userDto.isTemporaryPassword())
                .expirationDate(expirationDate)
                .roles(userRoles)
                .build();
        userRepository.save(user);

        return userMapper.mapUserToUserResponseDto(userRepository.save(user), UserResponseDto.builder().build());
    }

    @Override
    public UserResponseDto updateUser(String name, UserRegisterDto userDto) {
        User existingUser = userRepository.getUserByUsername(name)
                .orElseThrow(() -> new SecurityResourceNotFoundException("User", "username", userDto.getUsername()));

        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(userDto.getPassword());
            existingUser.setPassword(encodedPassword);
        }
        existingUser.setEnabled(userDto.isEnabled());
        existingUser.setTemporaryPassword(userDto.isTemporaryPassword());
        if (userDto.getExpirationDuration() != null && userDto.getExpirationDuration() != 0) {
            Timestamp expirationDate = new Timestamp(System.currentTimeMillis() + (userDto.getExpirationDuration() * 24 * 60 * 60 * 1000));
            existingUser.setExpirationDate(expirationDate);
        }
        if (userDto.getRoles() != null && !userDto.getRoles().isEmpty()) {
            List<Role> userRoles = roleRepository.getRolesByNames(userDto.getRoles());
            existingUser.setRoles(userRoles);
        }
        return userMapper.mapUserToUserResponseDto(userRepository.save(existingUser), UserResponseDto.builder().build());
    }
    @Override
    public List<UserResponseDto> getAllUserProfiles() {
        List<User> users = userRepository.findAll();
        return userMapper.mapUsersToUserResponseDtos(users);
    }
    @Override
    public void deleteUser(String name) {
        User user = userRepository.getUserByUsername(name)
                .orElseThrow(() -> new SecurityResourceNotFoundException("User", "username", name));
        userRepository.delete(user);
    }
}
