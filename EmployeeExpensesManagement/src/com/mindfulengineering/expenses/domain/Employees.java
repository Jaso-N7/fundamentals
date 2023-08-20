package com.mindfulengineering.expenses.domain;

import com.mindfulengineering.expenses.exceptions.EmployeeNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A wrapper class for holding an array of Employee(s)
 *
 * @author jason
 */
public class Employees {

    private final Map<Integer, Employee> employees = new HashMap<>();

    /**
     * Adds an employee
     *
     * @param employee
     */
    public void add(Employee employee) {
        employees.put(employee.getId(), employee);
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

        int eId = claim.getEmployeeId();
        
        if (!employeeExists(eId)) {
            throw new EmployeeNotFoundException();
        }
        
        Employee e = findById(eId);
        if (e != null) {
            e.addClaim(claim);
        }
    }

    public void viewEmployees() {

        List<Employee> el = new LinkedList<>(employees.values());
        Collections.sort(el);
        
        for (Employee e : el) {
            System.out.println(e);
            
            for (ExpenseClaim ec : e.getClaims().values()) {
                System.out.println(ec);
                ec.viewExpenseItems();
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

        for (Employee e : employees.values()) {
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

        return employees.get(employeeId);
        
    }

    public boolean employeeExists (int id) {
        return employees.containsKey(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (var e : employees.values()) {
            sb.append(e).append('\n');
        }
        return sb.toString();
    }

}
