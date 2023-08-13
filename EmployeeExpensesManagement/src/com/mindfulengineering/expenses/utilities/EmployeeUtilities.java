/*
 * Code Structure Part 2 Activity
 * Imports and package names
 */
package com.mindfulengineering.expenses.utilities;

import com.mindfulengineering.expenses.domain.Employee;
import com.mindfulengineering.expenses.domain.Employees;

/**
 *
 * @author jason
 */
public class EmployeeUtilities {
    
    public static boolean employeeExists (Employees emps, Employee emp) {
        
        return (emps.findBySurname(emp.getSurname()) != null);
    }
}
