package com.hahn.software.service;

import com.hahn.software.tools.EmployeeSystemConstants;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ComponentHelperTools {
    public static JTextField createPlaceholderTextField(String placeholder) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);
        return textField;
    }
    public static JPasswordField createPlaceholderPasswordField(String placeholder) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setForeground(Color.GRAY);
        passwordField.setText(placeholder);
        passwordField.setEchoChar((char) 0);
        return passwordField;
    }
    public static void customizeButton(JButton button) {
        button.setBackground(EmployeeSystemConstants.PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setBorder(new LineBorder(Color.GRAY, 2, true));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
    }
}
