package com.hahn.software.tools;

import javax.swing.*;

public class SwingDesignTools {
    public static void setPasswordFieldBorderRadius(JPasswordField passwordField, Integer radius) {
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                passwordField.getBorder(),
                BorderFactory.createEmptyBorder(radius, radius, radius, radius)
        ));
    }
    public static void setTextFieldBorderRadius(JTextField textField, Integer radius) {
        textField.setBorder(BorderFactory.createCompoundBorder(
                textField.getBorder(),
                BorderFactory.createEmptyBorder(radius, radius, radius, radius)
        ));
    }
}
