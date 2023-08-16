package com.mindfulengineering.expenses.ui;

import com.mindfulengineering.expenses.domain.Department;
import com.mindfulengineering.expenses.domain.Employee;
import com.mindfulengineering.expenses.domain.Employees;
import com.mindfulengineering.expenses.domain.ExpenseClaim;
import com.mindfulengineering.expenses.exceptions.InvalidEmployeeIdException;
import com.mindfulengineering.expenses.exceptions.NameTooShortException;
import com.mindfulengineering.expenses.utilities.EmployeeUtilities;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Scanner;

/**
 *
 * @author jason
 */
public class UIFunctions {

    private final static String MENU = """
                          Expense Management System
                          -------------------------
                          e - register new employee
                          c - register new claim
                          p - print all employees
                          x - exit""";
    
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Employees employees = new Employees(20);
        
        char choice;
        do {
            System.out.println(MENU);
            choice = scanner.nextLine().toLowerCase().charAt(0);

            switch (choice) {
                case 'e' ->
                    employees.add(registerNewEmployee());
                case 'c' -> employees.add(registerNewExpenseClaim());
                case 'p' -> employees.viewEmployees();
                case 'x' -> {
                    break;
                }
                default ->
                    System.out.println("Invalid choice");

            }
        } while (choice != 'x');

    }

    private static Employee registerNewEmployee() {

        Integer eid = getEmployeeId();

        System.out.println("Enter the title");
        String title = scanner.nextLine();

        String name[] = getEmployeeName();

        System.out.println("Enter the job title");
        String job = scanner.nextLine();

        Department d = getEmployeeDept();

        return new Employee(eid, title, name[0], name[1], job, d);
    }

    /**
     * Helper class for registerNewEmployee that validates and returns
     * the Employee ID
     * 
     * @return an Integer object representing the Employee ID
     */
    private static Integer getEmployeeId() {

        Integer eid;
        try {
            System.out.println("Enter the id");
            String empId = scanner.nextLine();
            eid = EmployeeUtilities.validateEmployeeId(empId);
        } catch (InvalidEmployeeIdException iex) {
            System.out.println("The id you entered was invalid - try again.");
            return getEmployeeId();
        }

        return eid;
    }

    /**
     * A helper class for registerNewEmployee, that validates and returns the
     * employees name.
     * 
     * @hidden Not using List<String> for this as yet
     * @return An array containing the employees first and last name, in the 
     * respective indices.
     */
    private static String[] getEmployeeName() {

        System.out.println("Enter the first name");
        String fn = scanner.nextLine();

        System.out.println("Enter the surname");
        String sn = scanner.nextLine();

        try {
            EmployeeUtilities.validateEmployeeName(fn, sn);
        } catch (NameTooShortException tsx) {
            System.out.println("The name you entered was not valid - try again.");
            return getEmployeeName();
        }

        return new String[]{fn, sn};
    }

    /**
     * A helper method to registerNewEmployee that validates and returns the employee's
     * Department
     * 
     * @return Department that the employee works in
     */
    private static Department getEmployeeDept() {
        try {
            
            System.out.println("Enter the department");
            String dept = scanner.nextLine();
            return Department.valueOf(dept.toUpperCase());
            
        } catch (IllegalArgumentException | NullPointerException x) {
            
            System.out.println("The department you entered was not valid - try again.");
            return getEmployeeDept();
            
        }
    }
    
    public static ExpenseClaim registerNewExpenseClaim () {
    
        System.out.println("Enter the claim Id");
        int cid = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Enter the employee Id");
        int eid = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Enter the amount");
        Double amount = scanner.nextDouble();
        scanner.nextLine();
        
        return new ExpenseClaim.Builder(cid, eid, ZonedDateTime.now(), amount)
                .build();
    }
}
