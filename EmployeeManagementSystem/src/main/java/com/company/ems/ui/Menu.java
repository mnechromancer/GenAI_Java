package com.company.ems.ui;

import com.company.ems.model.Employee;
import com.company.ems.service.EmployeeService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private EmployeeService employeeService;
    private DateTimeFormatter formatter;

    public Menu() {
        scanner = new Scanner(System.in);
        employeeService = new EmployeeService();
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Add employee");
            System.out.println("2. Update employee");
            System.out.println("3. Delete employee");
            System.out.println("4. Show employee details");
            System.out.println("5. Show all employees");
            System.out.println("0. Exit");

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

        System.out.println("Enter the department:");
        String department = scanner.nextLine();

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

        System.out.println("Enter the new department:");
        String department = scanner.nextLine();

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
            System.out.println("Department: " + employee.getDepartment());
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
            System.out.println("Department: " + employee.getDepartment());
        }
        System.out.println("------------------------------");
    }
}
