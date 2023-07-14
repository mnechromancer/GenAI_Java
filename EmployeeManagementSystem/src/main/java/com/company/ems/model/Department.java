package com.company.ems.model;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String id;
    private String name;
    private List<Employee> employees;

    public Department(String id, String name) {
        this.id = id;
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees); // return a copy to maintain encapsulation
    }

    public void addEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            employees.add(employee);
        }
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    @Override
    public String toString() {
        return "Department Name: " + name + ", Department ID: " + id;
    }
}