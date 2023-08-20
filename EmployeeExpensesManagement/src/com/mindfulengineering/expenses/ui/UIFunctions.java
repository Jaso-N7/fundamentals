package com.mindfulengineering.expenses.ui;

import com.mindfulengineering.expenses.domain.*;
import com.mindfulengineering.expenses.exceptions.*;
import com.mindfulengineering.expenses.utilities.EmployeeUtilities;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jason
 */
public class UIFunctions {

    private final Scanner scanner;

    public UIFunctions() {
        scanner = new Scanner(System.in);
    }

    public Employee registerNewEmployee(boolean isStaff) {

        Integer eid = getEmployeeId();

        System.out.println("Enter the title");
        String title = scanner.nextLine();

        String name[] = getEmployeeName();

        System.out.println("Enter the job title");
        String job = scanner.nextLine();

        Department d = getEmployeeDept();

        Employee e = new Employee(eid, title, name[0], name[1], job, d);
        
        if (isStaff) {

            System.out.println("Enter username");
            String u = scanner.nextLine();

            System.out.println("Enter password");
            String p = scanner.nextLine();

            return StaffEmployee.valueOf(e, u, p);
            
        } else {
            return e;
        }
    }

    /**
     * Helper class for registerNewEmployee that validates and returns the
     * Employee ID
     *
     * @return an Integer object representing the Employee ID
     */
    private Integer getEmployeeId() {

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
    private String[] getEmployeeName() {

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
     * A helper method to registerNewEmployee that validates and returns the
     * employee's Department
     *
     * @return Department that the employee works in
     */
    private Department getEmployeeDept() {
        try {

            System.out.println("Enter the department");
            String dept = scanner.nextLine();
            return Department.valueOf(dept.toUpperCase());

        } catch (IllegalArgumentException | NullPointerException x) {

            System.out.println("The department you entered was not valid - try again.");
            return getEmployeeDept();

        }
    }

    public ExpenseClaim registerNewExpenseClaim() {

        List<ExpenseItem> items = new LinkedList<>();
        ExpenseType et = null;
        
        System.out.println("Enter the claim Id");
        int cid = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the employee Id");
        int eid = scanner.nextInt();
        scanner.nextLine();

        while (true) {
            
            System.out.println("Enter the expense item Id");
            int xid = scanner.nextInt();
            scanner.nextLine();
            
            System.out.println("Enter the expense type (q - quit)");
            String type = scanner.nextLine();
            
            if (type.equalsIgnoreCase("Q")) { break; }
            
            try {
                et = ExpenseType.valueOf(type.toUpperCase());
            } catch (IllegalArgumentException | NullPointerException x) {
                System.out.println("Invalid expense type " + et);
                continue;
            }
            
            
            
            System.out.println("Describe the expense");
            String desc = scanner.nextLine();
            
            System.out.println("How much did it cost");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            
            items.add(ExpenseItem.create(xid, cid, et, desc, amount));
            
            System.out.println("Do you wish to add more (y/n)?");
            String ans = scanner.nextLine();
            
            if (ans.equalsIgnoreCase("N")) { break; }
        }
        
        if (items.isEmpty()) {
            System.out.println("You need to add at least one(1) expense type...");
            return registerNewExpenseClaim();
        }
        
        return new ExpenseClaim.Builder(cid, eid, ZonedDateTime.now())
                .expense(items)
                .build();
    }
}
