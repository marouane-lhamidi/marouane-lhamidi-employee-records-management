package com.hahn.software.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hahn.software.model.DepartmentDto;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class DepartmentBackendService {
    private static final String BASE_URL = "http://localhost:8080/api/departments";

    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<DepartmentDto> fetchAllDepartments() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/fetchAll"))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return objectMapper.readValue(response.body(), new TypeReference<List<DepartmentDto>>() {});
            } else {
                ExceptionHandling.handleResponseError("Failed to fetch departments", response.statusCode(), response.body());
            }
        } catch (Exception e) {
            ExceptionHandling.handleExceptionError(e);
        }
        return List.of();
    }

    public static DepartmentDto fetchDepartmentByName(String departmentName) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/fetch?departmentName=" + departmentName))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return objectMapper.readValue(response.body(), DepartmentDto.class);
            } else {
                ExceptionHandling.handleResponseError("Failed to fetch department", response.statusCode(), response.body());
            }
        } catch (Exception e) {
            ExceptionHandling.handleExceptionError(e);
        }
        return null;
    }

    public static boolean createDepartment(DepartmentDto department) throws JsonProcessingException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/create"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(department)))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 201) {
                ExceptionHandling.handleSuccessResponse("Department created successfully!");
                return true;
            } else {
                ExceptionHandling.handleResponseError("Failed to create department", response.statusCode(), response.body());
                return false;
            }
        } catch (Exception e) {
            ExceptionHandling.handleExceptionError(e);
            return false;
        }
    }


    public static boolean updateDepartment(DepartmentDto department) throws JsonProcessingException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/update"))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(department)))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                ExceptionHandling.handleSuccessResponse("Department updated successfully!");
                return true;
            } else {
                ExceptionHandling.handleResponseError("Failed to update department", response.statusCode(), response.body());
                return false;
            }
        } catch (Exception e) {
            ExceptionHandling.handleExceptionError(e);
            return false;
        }
    }


    public static void deleteDepartment(String departmentName) throws UnsupportedEncodingException {
        String encodedDepartmentName = URLEncoder.encode(departmentName, StandardCharsets.UTF_8.toString());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/delete?departmentName=" + encodedDepartmentName))
                .DELETE()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                ExceptionHandling.handleResponseError("Failed to delete department", response.statusCode(), response.body());
            }
        } catch (Exception e) {
            ExceptionHandling.handleExceptionError(e);
        }
    }
}
