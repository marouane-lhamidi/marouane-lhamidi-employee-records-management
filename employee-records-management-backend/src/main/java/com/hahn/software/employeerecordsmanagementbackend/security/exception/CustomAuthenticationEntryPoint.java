package com.hahn.software.employeerecordsmanagementbackend.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hahn.software.employeerecordsmanagementbackend.security.dto.SecurityErrorResponse;
import com.hahn.software.employeerecordsmanagementbackend.security.utils.SecurityErrorMessages;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String errorMessage;
        if (authException instanceof BadCredentialsException) {
            errorMessage = SecurityErrorMessages.BAD_CREDENTIALS;
        } else if (authException instanceof AccountExpiredException) {
            errorMessage = SecurityErrorMessages.ACCOUNT_EXPIRED;
        } else if (authException instanceof CredentialsExpiredException) {
            errorMessage = SecurityErrorMessages.CREDENTIALS_EXPIRED;
        } else {
            errorMessage = SecurityErrorMessages.UNAUTHORIZED; // Default message
        }

        // Build the error response
        SecurityErrorResponse errorResponse = SecurityErrorResponse.builder()
                .apiPath(request.getRequestURI())
                .errorCode(HttpStatus.UNAUTHORIZED)
                .errorMessage(errorMessage)
                .errorTime(LocalDateTime.now())
                .build();

        // Write the error response as JSON
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}