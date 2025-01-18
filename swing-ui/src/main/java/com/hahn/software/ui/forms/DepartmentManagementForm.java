package com.hahn.software.ui.forms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hahn.software.calback.FormCloseCallback;
import com.hahn.software.model.DepartmentDto;
import com.hahn.software.service.ComponentHelperTools;
import com.hahn.software.service.DepartmentBackendService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DepartmentManagementForm extends JFrame {
    private final JTable departmentsTable;
    private final DefaultTableModel tableModel;
    private final FormCloseCallback callback;

    public DepartmentManagementForm(FormCloseCallback callback) {
        this.callback = callback;
        setTitle("Manage Departments");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        tableModel = new DefaultTableModel(new String[]{"ID", "Name"}, 0);
        departmentsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(departmentsTable);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(scrollPane, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;

        JButton addButton = new JButton("Add Department");
        gbc.gridx = 0;
        ComponentHelperTools.customizeButton(addButton);
        add(addButton, gbc);

        JButton updateButton = new JButton("Update Department");
        ComponentHelperTools.customizeButton(updateButton);
        gbc.gridx = 1;
        add(updateButton, gbc);

        JButton deleteButton = new JButton("Delete Department");
        ComponentHelperTools.customizeButton(deleteButton);
        gbc.gridx = 2;
        add(deleteButton, gbc);

        addButton.addActionListener(e -> handleAddDepartment());
        updateButton.addActionListener(e -> handleUpdateDepartment());
        deleteButton.addActionListener(e -> handleDeleteDepartment());

        fetchDepartments();
    }

    private void fetchDepartments() {
        try {
            List<DepartmentDto> departments = DepartmentBackendService.fetchAllDepartments();
            tableModel.setRowCount(0); // Clear existing rows
            for (DepartmentDto department : departments) {
                tableModel.addRow(new Object[]{
                        department.getId(),
                        department.getName()
                });
            }
        } catch (Exception e) {
            showErrorDialog("Error fetching departments: " + e.getMessage());
        }
    }

    private void handleAddDepartment() {
        try {
            String name = JOptionPane.showInputDialog(this, "Enter Department Name:");
            if (name != null && !name.trim().isEmpty()) {
                DepartmentDto newDepartment = new DepartmentDto();
                newDepartment.setName(name);
                DepartmentBackendService.createDepartment(newDepartment);
                fetchDepartments();
            }
        } catch (JsonProcessingException e) {
            showErrorDialog("Error adding department: " + e.getMessage());
        }
    }

    private void handleUpdateDepartment() {
        int selectedRow = departmentsTable.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                Long departmentId = (Long) tableModel.getValueAt(selectedRow, 0);
                String newName = JOptionPane.showInputDialog(this, "Enter New Department Name:");
                if (newName != null && !newName.trim().isEmpty()) {
                    DepartmentDto updatedDepartment = new DepartmentDto();
                    updatedDepartment.setId(departmentId);
                    updatedDepartment.setName(newName);
                    DepartmentBackendService.updateDepartment(updatedDepartment);
                    fetchDepartments();
                }
            } catch (JsonProcessingException e) {
                showErrorDialog("Error updating department: " + e.getMessage());
            }
        } else {
            showWarningDialog("Select a department to update.");
        }
    }

    private void handleDeleteDepartment() {
        int selectedRow = departmentsTable.getSelectedRow();
        if (selectedRow >= 0) {
            String departmentName = (String) tableModel.getValueAt(selectedRow, 1);
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this department? All employees in this department will also be deleted.",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    DepartmentBackendService.deleteDepartment(departmentName);
                    fetchDepartments();
                    if (callback != null) {
                        callback.onDepartmentFormClosed();
                    }
                } catch (Exception e) {
                    showErrorDialog("Error deleting department: " + e.getMessage());
                }
            }
        } else {
            showWarningDialog("Select a department to delete.");
        }
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showWarningDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }
}
