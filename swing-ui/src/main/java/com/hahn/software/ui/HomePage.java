package com.hahn.software.ui;

import com.hahn.software.tools.EmployeeSystemConstants;
import com.hahn.software.ui.panel.EmployeesPage;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {

    public HomePage() {
        initializeFrame();
        addComponents();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("Employee Management");
        setSize(1080, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void addComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        mainPanel.add(createTitlePanel(), BorderLayout.NORTH);

        mainPanel.add(new EmployeesPage(), BorderLayout.CENTER);

        add(mainPanel);
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setBackground(EmployeeSystemConstants.PRIMARY_COLOR);
        titlePanel.setPreferredSize(new Dimension(1080, 40));

        JLabel titleLabel = new JLabel("Employee Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);

        titlePanel.add(titleLabel);

        return titlePanel;
    }
}
