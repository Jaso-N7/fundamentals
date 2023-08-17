package com.mindfulengineering.expenses.domain;

import com.mindfulengineering.expenses.exceptions.EmployeeNotFoundException;
import java.util.HashSet;
import java.util.Objects;

/**
 * A wrapper class for holding an array of Employee(s)
 *
 * @author jason
 */
public class Employees {

    private final HashSet<Employee> employees = new HashSet<>();

    /**
     * Adds an employee
     *
     * @param employee
     */
    public void add(Employee employee) {
        employees.add(employee);
    }

    /**
     * Adds an Expense Claim to the identified Employee
     *
     * @param claim
     * @throws EmployeeNotFoundException when the Employee has not been
     * registered
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
            System.out.println(e);
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
            if (surname.equalsIgnoreCase(e.getSurname())) {
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
            if (e.getId() == employeeId) {
                return e;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (var e : employees) {
            sb.append(e).append('\n');
        }
        return sb.toString();
    }

}
