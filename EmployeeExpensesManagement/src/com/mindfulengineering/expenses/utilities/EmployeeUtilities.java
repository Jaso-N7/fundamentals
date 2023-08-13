/*
 * Code Structure Part 2 Activity
 * Imports and package names
 */
package com.mindfulengineering.expenses.utilities;

import com.mindfulengineering.expenses.domain.Employee;
import com.mindfulengineering.expenses.domain.Employees;
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
}
