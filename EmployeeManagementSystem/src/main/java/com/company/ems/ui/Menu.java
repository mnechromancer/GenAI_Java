package com.company.ems.ui;

import com.company.ems.model.Department;
import com.company.ems.model.Employee;
import com.company.ems.service.DepartmentService;
import com.company.ems.service.EmployeeService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private EmployeeService employeeService = new EmployeeService();
    private DepartmentService departmentService = new DepartmentService();  // New field
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    public Menu() {
        scanner = new Scanner(System.in);
        employeeService = new EmployeeService();
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n-------------------------------------");
            System.out.println("Welcome to Employee Management System");
            System.out.println("-------------------------------------");
            System.out.println("1. Add an employee");
            System.out.println("2. Update an employee");
            System.out.println("3. Remove an employee");
            System.out.println("4. Show an employee's information");
            System.out.println("5. List all employees");
            System.out.println("6. List all departments");
            System.out.println("7. Add a department");
            System.out.println("8. Update a department");
            System.out.println("9. Delete a department");
            System.out.println("0. Exit");
            System.out.println("-------------------------------------");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline left-over

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    updateEmployee();
                    break;
                case 3:
                    deleteEmployee();
                    break;
                case 4:
                    showEmployee();
                    break;
                case 5:
                    showAllEmployees();
                    break;
                case 6:
                    showAllDepartments();
                    break;
                case 7:
                    addDepartment();
                    break;
                case 8:
                    updateDepartment();
                    break;
                case 9:
                    deleteDepartment();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void addEmployee() {
        System.out.println("Enter the employee's first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter the employee's last name:");
        String lastName = scanner.nextLine();

        System.out.println("Enter the employee ID:");
        String employeeId = scanner.nextLine();

        System.out.println("Enter the employment date (dd-MM-yyyy):");
        String date = scanner.nextLine();
        LocalDate dateOfEmployment = LocalDate.parse(date, formatter);

        System.out.println("Enter the salary:");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // consume newline left-over

        System.out.println("Enter the department ID:");
        String departmentId = scanner.nextLine();

        Department department = departmentService.getDepartment(departmentId);
        if (department == null) {
            System.out.println("Invalid department ID!");
            return;
        }

        Employee newEmployee = new Employee(firstName, lastName, employeeId, dateOfEmployment, salary, department);
        employeeService.addEmployee(newEmployee);

        System.out.println("Employee added successfully!");
    }

    private void updateEmployee() {
        System.out.println("Enter the employee ID of the employee to update:");
        String employeeId = scanner.nextLine();

        System.out.println("Enter the new first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter the new last name:");
        String lastName = scanner.nextLine();

        System.out.println("Enter the new employment date (dd-MM-yyyy):");
        String date = scanner.nextLine();
        LocalDate dateOfEmployment = LocalDate.parse(date, formatter);

        System.out.println("Enter the new salary:");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // consume newline left-over

        System.out.println("Enter the new department ID:");
        String departmentId = scanner.nextLine();

        Department department = departmentService.getDepartment(departmentId);
        if (department == null) {
            System.out.println("Invalid department ID!");
            return;
        }

        Employee updatedEmployee = new Employee(firstName, lastName, employeeId, dateOfEmployment, salary, department);
        employeeService.updateEmployee(employeeId, updatedEmployee);

        System.out.println("Employee updated successfully!");
    }

    private void deleteEmployee() {
        System.out.println("Enter the employee ID of the employee to delete:");
        String employeeId = scanner.nextLine();

        employeeService.deleteEmployee(employeeId);

        System.out.println("Employee deleted successfully!");
    }

    private void showEmployee() {
        System.out.println("Enter the employee ID of the employee to show:");
        String employeeId = scanner.nextLine();

        Employee employee = employeeService.getEmployee(employeeId);

        if (employee != null) {
            System.out.println("First Name: " + employee.getFirstName());
            System.out.println("Last Name: " + employee.getLastName());
            System.out.println("Employee ID: " + employee.getEmployeeId());
            System.out.println("Date of Employment: " + employee.getDateOfEmployment().format(formatter));
            System.out.println("Salary: " + employee.getSalary());
            System.out.println("Department: " + employee.getDepartment().toString());
        } else {
            System.out.println("Employee not found!");
        }
    }

    private void showAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();

        for (Employee employee : employees) {
            System.out.println("------------------------------");
            System.out.println("First Name: " + employee.getFirstName());
            System.out.println("Last Name: " + employee.getLastName());
            System.out.println("Employee ID: " + employee.getEmployeeId());
            System.out.println("Date of Employment: " + employee.getDateOfEmployment().format(formatter));
            System.out.println("Salary: " + employee.getSalary());
            System.out.println(employee.getDepartment().toString());
        }
        System.out.println("------------------------------");
    }

    private void showAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();

        for (Department department : departments) {
            System.out.println("------------------------------");
            System.out.println("Department ID: " + department.getId());
            System.out.println("Department Name: " + department.getName());
            System.out.println("Number of Employees: " + department.getEmployees().size());
        }
        System.out.println("------------------------------");
    }

    private void addDepartment() {
        System.out.println("Enter the department ID:");
        String id = scanner.nextLine();

        System.out.println("Enter the department name:");
        String name = scanner.nextLine();

        Department newDepartment = new Department(id, name);
        departmentService.addDepartment(newDepartment);

        System.out.println("Department added successfully!");
    }

    private void updateDepartment() {
        System.out.println("Enter the department ID to update:");
        String id = scanner.nextLine();

        Department department = departmentService.getDepartment(id);
        if (department == null) {
            System.out.println("No such department!");
            return;
        }

        System.out.println("Enter the new department name:");
        String name = scanner.nextLine();

        departmentService.removeDepartment(id);

        Department updatedDepartment = new Department(id, name);
        departmentService.addDepartment(updatedDepartment);

        System.out.println("Department updated successfully!");
    }

    private void deleteDepartment() {
        System.out.println("Enter the department ID to delete:");
        String id = scanner.nextLine();

        Department department = departmentService.getDepartment(id);
        if (department == null) {
            System.out.println("No such department!");
            return;
        }

        departmentService.removeDepartment(id);

        System.out.println("Department deleted successfully!");
    }
}
