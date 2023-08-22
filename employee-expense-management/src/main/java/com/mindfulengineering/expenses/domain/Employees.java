package com.mindfulengineering.expenses.domain;

import com.mindfulengineering.expenses.exceptions.EmployeeNotFoundException;
import java.util.List;

/**
 *
 * @author jason
 */
public interface Employees {

    /**
     * Adds an employee
     *
     * @param employee
     */
    void add(Employee employee);

    /**
     * Adds an Expense Claim to the identified Employee
     *
     * @param claim
     * @throws EmployeeNotFoundException when the Employee has not been
     * registered
     */
    void add(ExpenseClaim claim) throws EmployeeNotFoundException;

    boolean employeeExists(int id);

    /**
     * Finds an employee by their Employee ID
     *
     * Be mindful that the Employee registry needs to be populated first.
     *
     * @param employeeId - Employee's ID
     * @return Maybe the Employee object if exists
     * @throws EmployeeNotFoundException when the Employee is not found
     */
    Employee findById(Integer employeeId) throws EmployeeNotFoundException;

    /**
     * Finds an employee by their surname
     *
     * @param surname
     * @return An Employee record if found; Otherwise null
     * @throws NullPointerException
     */
    Employee findBySurname(String surname) throws NullPointerException;

    /**
     * Get all the employees that have been registered.
     *
     * @return A List of Employee(s)
     */
    List<Employee> getEmployees();

    String toString();

    void viewEmployees();
    
}
