/*
 * Code Structure Part 2 Activity
 * Imports and package names
 */
package com.mindfulengineering.expenses.utilities;

import com.mindfulengineering.expenses.domain.Employee;
import com.mindfulengineering.expenses.domain.Employees;
import com.mindfulengineering.expenses.exceptions.InvalidEmployeeIdException;
import java.util.Objects;

/**
 *
 * @author jason
 */
public class EmployeeUtilities { 
    
    public static boolean employeeExists (Employees emps, Employee emp) {
        
        Objects.requireNonNull(emps, "A valid list of Employees must be provided");
        Objects.requireNonNull(emp, "A valid Employee must be provided");
        
        return (emps.findBySurname(emp.getSurname()) != null);
    }
    
    /**
     * Returns an Integer object holding the value of the Employee ID.
     * 
     * @param empId - The Employee ID to be parsed
     * @return An Integer object holding the Employee ID represented by the string
     * argument.
     * @throws InvalidEmployeeIdException if the string cannot be recognized as a
     * valid Employee ID.
     */
    public static Integer validateEmployeeId (String empId)
            throws InvalidEmployeeIdException {
        
        Objects.requireNonNull(empId, "An employee id is required");
        
        if (empId.isBlank() || empId.isEmpty()) {
            throw new InvalidEmployeeIdException();
        }
        
        try {
            Integer eId = Integer.valueOf(empId);
            return eId;
        } catch (NumberFormatException nfe) {
            throw new InvalidEmployeeIdException();
        }
    }
}
