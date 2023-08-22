/*
 * Code Structure Part 2 Activity
 * Imports and package names
 */
package com.mindfulengineering.expenses.utilities;

import com.mindfulengineering.expenses.exceptions.InvalidEmployeeIdException;
import com.mindfulengineering.expenses.exceptions.NameTooShortException;
import java.util.Objects;

/**
 *
 * @author jason
 */
public class EmployeeUtilities { 
    
      
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
    
    /**
     * Ensuring that the employee name is valid.
     * 
     * @param firstName - Employee's first name
     * @param surname - Employee's last name
     * @throws NameTooShortException 
     */
    public static void validateEmployeeName (String firstName, String surname)
            throws NameTooShortException {
        
        Objects.requireNonNull(firstName, "First name is required");
        Objects.requireNonNull(surname, "Surname is required");
        
        if (firstName.isBlank() || firstName.isEmpty()) {
            throw new NameTooShortException();
        }
        if (surname.isBlank() || surname.isEmpty()) {
            throw new NameTooShortException();
        }
        
        if (firstName.length() + surname.length() < 6)
            throw new NameTooShortException();
        
    }
}
