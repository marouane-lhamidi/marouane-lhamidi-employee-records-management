package com.hahn.software.ui.panel;

import com.hahn.software.calback.FormCloseCallback;
import com.hahn.software.model.EmployeeDto;
import com.hahn.software.service.ComponentHelperTools;
import com.hahn.software.service.EmployeeBackendService;
import com.hahn.software.ui.forms.DepartmentManagementForm;
import com.hahn.software.ui.forms.EmployeeForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class EmployeesPage extends JPanel implements FormCloseCallback {
    private JTable employeesTable;
    private DefaultTableModel tableModel;

    public EmployeesPage() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Employees", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Job Title", "Department"}, 0);
        employeesTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeesTable);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 3.0;
        add(scrollPane, gbc);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(buttonPanel, gbc);

        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(5, 10, 5, 10);
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        buttonGbc.weightx = 1;
        buttonGbc.weighty = 1;


        JButton addButton = new JButton("Add Employee");
        ComponentHelperTools.customizeButton(addButton);
        buttonPanel.add(addButton, buttonGbc);

        buttonGbc.gridx++;
        JButton updateButton = new JButton("Update Employee");
        ComponentHelperTools.customizeButton(updateButton);
        buttonPanel.add(updateButton, buttonGbc);

        buttonGbc.gridx++;
        JButton deleteButton = new JButton("Delete Employee");
        ComponentHelperTools.customizeButton(deleteButton);
        buttonPanel.add(deleteButton, buttonGbc);

        buttonGbc.gridx++;
        JButton detailsButton = new JButton("Show Details");
        ComponentHelperTools.customizeButton(detailsButton);
        buttonPanel.add(detailsButton, buttonGbc);

        buttonGbc.gridx++;
        JButton manageDepartmentsButton = new JButton("Manage Departments");
        ComponentHelperTools.customizeButton(manageDepartmentsButton);
        buttonPanel.add(manageDepartmentsButton, buttonGbc);

        fetchEmployees();

        addButton.addActionListener(e -> showEmployeeForm(null));
        updateButton.addActionListener(e -> updateEmployee());
        deleteButton.addActionListener(e -> deleteEmployee());
        detailsButton.addActionListener(e -> showEmployeeDetails());
        manageDepartmentsButton.addActionListener(e -> showDepartmentManagement());
    }

    private void fetchEmployees() {
        try {
            List<EmployeeDto> employees = EmployeeBackendService.fetchAllEmployees();
            tableModel.setRowCount(0);
            for (EmployeeDto employee : employees) {
                tableModel.addRow(new Object[]{
                        employee.getEmployeeId(),
                        employee.getFullName(),
                        employee.getJobTitle(),
                        employee.getDepartmentName()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching employees: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showEmployeeForm(EmployeeDto employee) {
        EmployeeForm form = new EmployeeForm(employee, this::fetchEmployees);
        form.setVisible(true);
    }

    private void updateEmployee() {
        int selectedRow = employeesTable.getSelectedRow();
        if (selectedRow >= 0) {
            String employeeId = (String) tableModel.getValueAt(selectedRow, 0);
            EmployeeDto employee = EmployeeBackendService.fetchEmployeeById(employeeId);
            showEmployeeForm(employee);
        } else {
            JOptionPane.showMessageDialog(this, "Select an employee to update.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteEmployee() {
        int selectedRow = employeesTable.getSelectedRow();
        if (selectedRow >= 0) {
            String employeeId = (String) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this employee?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                EmployeeBackendService.deleteEmployee(employeeId);
                fetchEmployees();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select an employee to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void showEmployeeDetails() {
        int selectedRow = employeesTable.getSelectedRow();
        if (selectedRow >= 0) {
            String employeeId = (String) tableModel.getValueAt(selectedRow, 0);
            EmployeeDto employee = EmployeeBackendService.fetchEmployeeById(employeeId);
            if (employee != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                JOptionPane.showMessageDialog(this,
                        "ID: " + employee.getEmployeeId() + "\n" +
                                "Name: " + employee.getFullName() + "\n" +
                                "Job Title: " + employee.getJobTitle() + "\n" +
                                "Department: " + employee.getDepartmentName() + "\n" +
                                "Hiring Date: " + dateFormat.format(employee.getHireDate()) + "\n" +
                                "Email: " + employee.getEmail() + "\n" +
                                "Phone: " + employee.getPhoneNumber() + "\n" +
                                "Address: " + employee.getAddress() + "\n",
                        "Employee Details",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error fetching employee details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select an employee to view details.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void showDepartmentManagement() {
        DepartmentManagementForm departmentForm = new DepartmentManagementForm(this);
        departmentForm.setVisible(true);
    }

    @Override
    public void onDepartmentFormClosed() {
        fetchEmployees();
    }
}
