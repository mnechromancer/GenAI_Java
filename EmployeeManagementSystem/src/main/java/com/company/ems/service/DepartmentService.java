package com.company.ems.service;

import com.company.ems.model.Department;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentService {
    private Map<String, Department> departments = new HashMap<>();

    public void addDepartment(Department department) {
        departments.put(department.getId(), department);
    }

    public Department getDepartment(String id) {
        return departments.get(id);
    }

    public List<Department> getAllDepartments() {
        return new ArrayList<>(departments.values());
    }

    public void removeDepartment(String id) {
        departments.remove(id);
    }
}