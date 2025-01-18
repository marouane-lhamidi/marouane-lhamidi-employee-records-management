package com.hahn.software.model;

import com.hahn.software.model.enums.EmploymentStatus;

import java.util.Date;

public class EmployeeDto {
    private String employeeId;
    private String fullName;
    private String jobTitle;
    private Date hireDate;
    private String email;
    private String phoneNumber;
    private String address;
    private EmploymentStatus employmentStatus;
    private String departmentName;

    public EmployeeDto() {
    }

    public EmployeeDto(String employeeId, String fullName, String jobTitle, Date hireDate, String email, String phoneNumber, EmploymentStatus employmentStatus, String departmentName) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.jobTitle = jobTitle;
        this.hireDate = hireDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.employmentStatus = employmentStatus;
        this.departmentName = departmentName;
    }

    public EmployeeDto(String fullName, String jobTitle, String email, String phoneNumber, String departmentName) {
        this.fullName = fullName;
        this.jobTitle = jobTitle;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.departmentName = departmentName;
    }


    // Getters and setters

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public EmploymentStatus getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(EmploymentStatus employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

