package com.hahn.software.ui;

import com.hahn.software.service.ComponentHelperTools;
import com.hahn.software.tools.EmployeeSystemConstants;
import com.hahn.software.tools.SwingDesignTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPage() {
        initializeFrame();
        addComponents();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("Hahn Software Employee Management");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void addComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridx = 0;

        gbc.insets = new Insets(10, 40, 10, 40);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Hahn Software Employee Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.DARK_GRAY);
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, gbc);

        JLabel loginLabel = new JLabel("Login", SwingConstants.CENTER);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 18));
        loginLabel.setForeground(EmployeeSystemConstants.PRIMARY_COLOR);
        gbc.gridy++;
        gbc.insets = new Insets(10, 100, 10, 100);
        mainPanel.add(loginLabel, gbc);

        usernameField = ComponentHelperTools.createPlaceholderTextField("Username");
        configureTextField(usernameField);
        gbc.gridy++;
        mainPanel.add(usernameField, gbc);

        passwordField = ComponentHelperTools.createPlaceholderPasswordField("Password");
        configurePasswordField(passwordField);
        gbc.gridy++;
        mainPanel.add(passwordField, gbc);

        loginButton = createLoginButton();
        gbc.gridy++;
        mainPanel.add(loginButton, gbc);

        add(mainPanel);
    }




    private void configureTextField(JTextField textField) {
        SwingDesignTools.setTextFieldBorderRadius(textField, EmployeeSystemConstants.BORDER_RADIUS_INPUTS);
        textField.setOpaque(false);
        setListenerOnTextField(textField);
    }

    private void configurePasswordField(JPasswordField passwordField) {
        SwingDesignTools.setPasswordFieldBorderRadius(passwordField, EmployeeSystemConstants.BORDER_RADIUS_INPUTS);
        passwordField.setOpaque(false);
        setListenerOnPasswordField(passwordField);
    }

    private void setListenerOnTextField(JTextField textField) {
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (textField.getText().equals("Username")) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText("Username");
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void setListenerOnPasswordField(JPasswordField passwordField) {
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (new String(passwordField.getPassword()).equals("Password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setEchoChar('●');
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (new String(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText("Password");
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setEchoChar((char) 0);
                }
            }
        });
    }

    private JButton createLoginButton() {
        JButton button = new JButton("Login");
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(EmployeeSystemConstants.PRIMARY_COLOR);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.addActionListener(new LoginButtonActionListener());
        return button;
    }

    private class LoginButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("admin") && password.equals("admin")) {
                Timer timer = new Timer(2000, evt -> {
                    JOptionPane.showMessageDialog(null, "Login Successful. Redirecting...");
                    openHomePage();
                });
                timer.setRepeats(false);
                timer.start();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void openHomePage() {
            HomePage homePage = new HomePage();
            homePage.setVisible(true);
            dispose();
        }
    }
}
