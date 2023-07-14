package com.company.ems.model;

import java.time.LocalDate;

public class Employee {
    private String firstName;
    private String lastName;
    private String employeeId;
    private LocalDate dateOfEmployment;
    private double salary;
    private Department department;

    public Employee(String firstName, String lastName, String employeeId, LocalDate dateOfEmployment, double salary, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.dateOfEmployment = dateOfEmployment;
        this.salary = salary;
        this.department = department;
    }

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        if (this.department != null) {
            this.department.removeEmployee(this);
        }

        this.department = department;

        if (department != null) {
            department.addEmployee(this);
        }
    }
}
