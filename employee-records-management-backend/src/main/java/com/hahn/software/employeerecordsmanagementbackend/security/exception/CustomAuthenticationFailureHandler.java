package com.hahn.software.employeerecordsmanagementbackend.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Authentication Failed");

        // Customize the error message based on the exception type
        if (exception instanceof BadCredentialsException) {
            errorResponse.put("message", "Invalid username or password");
        } else if (exception instanceof AccountExpiredException) {
            errorResponse.put("message", "Your account has expired");
        } else if (exception instanceof CredentialsExpiredException) {
            errorResponse.put("message", "Your credentials have expired");
        } else {
            errorResponse.put("message", exception.getMessage());
        }

        // Write the error response as JSON
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
