package com.hahn.software.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hahn.software.config.ConfigLoader;
import com.hahn.software.model.EmployeeDto;

import javax.swing.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class EmployeeBackendService {
    private static final String BASE_URL = ConfigLoader.get("api.backend") + "/api/employees";

    public static List<EmployeeDto> fetchAllEmployees() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/fetchAll"))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                // Parse JSON response
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(response.body(), new TypeReference<List<EmployeeDto>>() {});
            } else {
                ExceptionHandling.handleResponseError("Failed to fetch employees", response.statusCode(), response.body());
            }
        } catch (Exception e) {
            ExceptionHandling.handleExceptionError(e);
        }
        return List.of();
    }

    public static EmployeeDto fetchEmployeeById(String employeeId) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/fetch?employeeId=" + employeeId))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                // Parse JSON response
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(response.body(), EmployeeDto.class);
            } else {
                ExceptionHandling.handleResponseError("Failed to fetch employee", response.statusCode(), response.body());
            }
        } catch (Exception e) {
            ExceptionHandling.handleExceptionError(e);
        }
        return null;
    }

    public static boolean createEmployee(EmployeeDto employee) throws JsonProcessingException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/create"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(serializeEmployee(employee)))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 201) {
                ExceptionHandling.handleSuccessResponse("Employee created successfully!");
                return true;
            } else {
                ExceptionHandling.handleResponseError("Failed to create employee", response.statusCode(), response.body());
                return false;
            }
        } catch (Exception e) {
            ExceptionHandling.handleExceptionError(e);
            return false;
        }
    }

    public static boolean updateEmployee(EmployeeDto employee) throws JsonProcessingException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/update"))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(serializeEmployee(employee)))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                ExceptionHandling.handleSuccessResponse("The employee was updated successfully!");
                return true;
            } else {
                ExceptionHandling.handleResponseError("Failed to update employee", response.statusCode(), response.body());
                return false;
            }
        } catch (Exception e) {
            ExceptionHandling.handleExceptionError(e);
            return false;
        }
    }

    public static boolean deleteEmployee(String employeeId) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/delete?employeeId=" + employeeId))
                .DELETE()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                ExceptionHandling.handleSuccessResponse("Employee deleted successfully!");
                return true;
            } else {
                ExceptionHandling.handleResponseError("Failed to delete employee", response.statusCode(), response.body());
                return false;
            }
        } catch (Exception e) {
            ExceptionHandling.handleExceptionError(e);
            return false;
        }
    }

    private static String serializeEmployee(EmployeeDto employee) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(employee);
    }
}
