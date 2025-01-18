package com.hahn.software.ui.forms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hahn.software.model.DepartmentDto;
import com.hahn.software.model.EmployeeDto;
import com.hahn.software.model.enums.EmploymentStatus;
import com.hahn.software.service.ComponentHelperTools;
import com.hahn.software.service.DepartmentBackendService;
import com.hahn.software.service.EmployeeBackendService;
import com.hahn.software.service.ExceptionHandling;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

public class EmployeeForm extends JDialog {
    private JTextField fullNameField;
    private JTextField jobTitleField;
    private JTextField hireDateField;
    private JTextField emailField;
    private JTextField phoneNumberField;
    private JTextField addressField;
    private JComboBox<String> employmentStatusField;
    private JComboBox<String> departmentField;
    private Runnable onSaveCallback;

    public EmployeeForm(EmployeeDto employee, Runnable onSaveCallback) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.onSaveCallback = onSaveCallback;
        setTitle(employee == null ? "Add Employee" : "Edit Employee");
        setSize(650, 250);
        setLayout(new BorderLayout());
        setModal(true);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        formPanel.add(new JLabel("Full Name:"));
        fullNameField = new JTextField(employee != null ? employee.getFullName() : "");
        formPanel.add(fullNameField);

        formPanel.add(new JLabel("Job Title:"));
        jobTitleField = new JTextField(employee != null ? employee.getJobTitle() : "");
        formPanel.add(jobTitleField);

        formPanel.add(new JLabel("Hire Date (yyyy-MM-dd):"));
        hireDateField = new JTextField(employee != null && employee.getHireDate() != null
                ? dateFormat.format(employee.getHireDate())
                : "");
        formPanel.add(hireDateField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField(employee != null ? employee.getEmail() : "");
        formPanel.add(emailField);

        formPanel.add(new JLabel("Phone Number:"));
        phoneNumberField = new JTextField(employee != null ? employee.getPhoneNumber() : "");
        formPanel.add(phoneNumberField);

        formPanel.add(new JLabel("Address:"));
        addressField = new JTextField(employee != null ? employee.getAddress() : "");
        formPanel.add(addressField);

        formPanel.add(new JLabel("Employment Status:"));
        employmentStatusField = new JComboBox<>();
        for (EmploymentStatus status : EmploymentStatus.values()) {
            employmentStatusField.addItem(status.name());
        }
        if (employee != null && employee.getEmploymentStatus() != null) {
            employmentStatusField.setSelectedItem(employee.getEmploymentStatus().toString());
        }
        formPanel.add(employmentStatusField);

        formPanel.add(new JLabel("Department:"));
        departmentField = new JComboBox<>(getDepartments().toArray(new String[0]));
        if (employee != null) {
            departmentField.setSelectedItem(employee.getDepartmentName());
        }
        formPanel.add(departmentField);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton saveButton = new JButton("Save");
        ComponentHelperTools.customizeButton(saveButton);
        saveButton.addActionListener(e -> {
            try {
                saveEmployee(employee);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonPanel.add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        ComponentHelperTools.customizeButton(cancelButton);
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private List<String> getDepartments() {
        List<DepartmentDto> departmentDtos = DepartmentBackendService.fetchAllDepartments();
        return departmentDtos.stream().map(DepartmentDto::getName).toList();
    }

    private void saveEmployee(EmployeeDto employee) throws JsonProcessingException {
        if (employee == null) {
            employee = new EmployeeDto();
        }

        employee.setFullName(fullNameField.getText());
        employee.setJobTitle(jobTitleField.getText());

        try {
            String dateInput = hireDateField.getText().trim();
            if (dateInput.isEmpty()) {
                ExceptionHandling.handleSimpleError("Please enter a valid hire date.");
                return;
            }
            LocalDate hireDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Date utilDate = Date.from(hireDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            employee.setHireDate(utilDate);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid hire date. Please use the format yyyy-MM-dd.");
            return;
        }

        employee.setEmail(emailField.getText());
        employee.setPhoneNumber(phoneNumberField.getText());
        employee.setAddress(addressField.getText());

        employee.setEmploymentStatus(EmploymentStatus.valueOf((String) employmentStatusField.getSelectedItem()));
        employee.setDepartmentName((String) departmentField.getSelectedItem());

        boolean isSuccessful = false;
        if (employee.getEmployeeId() == null) {
            isSuccessful =EmployeeBackendService.createEmployee(employee);
        } else {
            isSuccessful =EmployeeBackendService.updateEmployee(employee);
        }
        if (isSuccessful){
            onSaveCallback.run();
            dispose();
        }
    }
}
