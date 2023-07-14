package com.company.ems.service;

import com.company.ems.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private List<Employee> employees;

    public EmployeeService() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public Employee getEmployee(String employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId().equals(employeeId)) {
                return employee;
            }
        }
        return null;
    }

    public void updateEmployee(String employeeId, Employee updatedEmployee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeId().equals(employeeId)) {
                employees.set(i, updatedEmployee);
                return;
            }
        }
    }

    public void deleteEmployee(String employeeId) {
        employees.removeIf(employee -> employee.getEmployeeId().equals(employeeId));
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }
}