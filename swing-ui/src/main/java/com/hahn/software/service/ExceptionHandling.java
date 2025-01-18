package com.hahn.software.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;

public class ExceptionHandling {
    public static void handleResponseError(String message, int statusCode, String responseBody) {
        String errorMessage = parseErrorMessage(responseBody);
        String messageContent = "<html><body style='width: 400px'>"
                + message + "<br>"
                + "Status Code: " + statusCode + "<br>"
                + "Response: " + errorMessage + "</body></html>";
        JOptionPane.showMessageDialog(null, messageContent, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleExceptionError(Exception e) {
        String message = "<html><body style='width: 400px'>"
                + "Error updating employee:<br>" + e.getMessage()
                + "</body></html>";
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public static void handleSimpleError(String message) {
        String messageContent = "<html><body style='width: 400px'>"
                +  message
                + "</body></html>";
        JOptionPane.showMessageDialog(null, messageContent, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleSuccessResponse(String message) {
        String messageContent = "<html><body style='width: 400px'>"
                + message +"<br>"
                + "</body></html>";
        JOptionPane.showMessageDialog(null, messageContent, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private static String parseErrorMessage(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseJson = objectMapper.readTree(responseBody);
            if (responseJson.has("errorMessage")) {
                return responseJson.get("errorMessage").asText();
            }
            if (responseJson.has("phoneNumber")) {
                return responseJson.get("phoneNumber").asText();
            } else if (responseJson.has("fullName")) {
                return responseJson.get("fullName").asText();
            } else if (responseJson.has("email")) {
                return responseJson.get("email").asText();
            }
        } catch (Exception e) {
            return "Failed to parse error: " + e.getMessage();
        }
        return "Unknown error occurred.";
    }


}
