package com.mindfulengineering.expenses.domain;

import com.mindfulengineering.expenses.exceptions.EmployeeNotFoundException;
import java.util.Objects;

/**
 * A wrapper class for holding an array of Employee(s)
 *
 * @author jason
 */
public class Employees {

    private final Employee[] employees;

    /**
     * Constructs a class with an array of employees
     *
     * @param numberOfEmployees Used to set the length of the array
     */
    public Employees(int numberOfEmployees) {
        employees = new Employee[numberOfEmployees];
    }

    /**
     * Adds an employee
     *
     * @param employee
     */
    public void add(Employee employee) {

        int firstEmptyPosition = -1;

        for (int i = 0; i < employees.length; i++) {
            if (firstEmptyPosition == -1 && employees[i] == null) {
                firstEmptyPosition = i;
            }
        }

        if (firstEmptyPosition == -1) {
            System.out.println("Sorry, the array is full");
        } else {
            employees[firstEmptyPosition] = employee;
        }
    }

    /**
     * Adds an Expense Claim to the identified Employee
     *
     * @param claim
     * @throws EmployeeNotFoundException when the Employee has not been registered
     */
    public void add(ExpenseClaim claim)
            throws EmployeeNotFoundException {

            Employee e = findById(claim.getEmployeeId());
            if (e != null) {
                e.addClaim(claim);
            }
    }

    public void viewEmployees() {

        for (Employee e : employees) {
            if (e != null) {
                System.out.println(e);
            }
        }

    }

    /**
     * Finds an employee by their surname
     *
     * @param surname
     * @return An Employee record if found; Otherwise null
     * @throws NullPointerException
     */
    public Employee findBySurname(String surname)
            throws NullPointerException {

        for (Employee e : employees) {
            if (e != null && surname.equalsIgnoreCase(e.getSurname())) {
                return e;
            }
        }

        return null;
    }

    /**
     * Finds an employee by their Employee ID
     *
     * Be mindful that the Employee registry needs to be populated first.
     *
     * @param employeeId - Employee's ID
     * @return Maybe the Employee object if exists
     * @throws EmployeeNotFoundException when the Employee is not found
     */
    public Employee findById(Integer employeeId)
            throws EmployeeNotFoundException {

        Objects.requireNonNull(employeeId, "An employee ID is required.");

        for (Employee e : employees) {
            if (e != null && e.getId() == employeeId) {
                return e;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (var e : employees) {
            if (e != null) {
                sb.append(e).append('\n');
            }
        }
        return sb.toString();
    }

}
