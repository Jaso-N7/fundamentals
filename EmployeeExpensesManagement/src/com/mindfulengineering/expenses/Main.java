package com.mindfulengineering.expenses;

import com.mindfulengineering.expenses.domain.Department;
import com.mindfulengineering.expenses.domain.ExpenseClaim;
import com.mindfulengineering.expenses.domain.ExpenseItem;
import com.mindfulengineering.expenses.domain.Employees;
import com.mindfulengineering.expenses.domain.Employee;
import com.mindfulengineering.expenses.domain.ExpenseType;
import com.mindfulengineering.expenses.domain.StaffEmployee;
import com.mindfulengineering.expenses.exceptions.EmployeeNotFoundException;
import com.mindfulengineering.expenses.utilities.EmployeeUtilities;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.Random;

/**
 *
 * @author jason
 */
public class Main {
    public static void main(String[] args) {
        
        Employee mac = new Employee();
        mac.setId(1);
        mac.setTitle("Mr.");
        mac.setFirstName("Angus");
        mac.setSurname("MacGuyver");
        
        System.out.println(mac.getMailingName());
        System.out.println(mac.getMailingName(true));
        System.out.println(mac.getMailingName(false));
        
        Employee dalton = new Employee(2, "Manager");
        dalton.setTitle("Dr.");
        dalton.setFirstName("Johnny");
        dalton.setSurname("Dalton");
        
        Employees employees = new Employees();
        employees.add(mac);
        employees.add(dalton);
        employees.add( new Employee(3, "Mrs.", "Susan", "Brown", 
                "Director", Department.INFOTECH) );
        
        employees.viewEmployees();
        
        System.out.println("Looking for 'Dalton' => " + employees.findBySurname("dalton"));
        
        Employee notFound = employees.findBySurname("James");
        System.out.println("Looking for 'James' => " + 
                ((notFound == null) ? "No employee found" : notFound));
        
        // Testing EmployeeUtilities.employeeExists
        System.out.println("Does Dalton exist? " + 
                ( EmployeeUtilities.employeeExists(employees, mac) ? "Yes" : "No" ));
        
        String james = EmployeeUtilities
                .employeeExists(employees, new Employee(666, "Mr.", "Chris", "James",
                        "Staff", Department.FINANCE)) 
                ? "Yes" : "No";
        System.out.println("Does James exist? " + james);
        System.out.println("");
        System.out.println(employees);
        
        // Testing expense claims
        System.out.println("\nTesting Expense Claims ---");
        ExpenseClaim expenseClaim = 
                new ExpenseClaim.Builder(1_001, mac.getId(),
                        ZonedDateTime.now(ZoneId.of("Jamaica")), 5_153.58)
                .build();
        System.out.println("New expense claim for employee ID: " + expenseClaim.getId());
        System.out.println("Attempting to pay for the expense:");
        ExpenseClaim claimNotApproved = expenseClaim.paid(true);
        System.out.println("Checking paid status: " + claimNotApproved.isPaid());
        System.out.println("Approving claim...");
        ExpenseClaim claimApproved = claimNotApproved.approved(true);
        System.out.println("Paying for claim...");
        ExpenseClaim claimPaid = claimApproved.paid(true);
        System.out.println("Paid: " + claimPaid.isPaid());
        System.out.println(claimPaid);
        
        // Testing expense items
        System.out.println("\nTesting Expense Items ---");
        ExpenseItem expenseItem = ExpenseItem.create(8_000, claimPaid.getId(), 
                ExpenseType.ACCOMODATION, "Rio Grande Ocho Rios, 2 nights", 75_156.65);
        System.out.println(expenseItem.getDescription());
        
        // Testing equality
        System.out.println("\nTesting Equality ---");
        System.out.println(mac.equals(dalton));
        System.out.println(mac == mac);
        System.out.println(mac.getClass());
        
        // Testing if employee exists
        System.out.println("\nTesting for employees existence ---");
        int rand = (int) (Math.random() * 5); 
        try {
            System.out.println(employees.findById(rand));
        } catch (EmployeeNotFoundException enf) {
            System.out.println("Employee " + rand + " not found");
        }
        
        System.out.println("Testing Staff ---");
        StaffEmployee staff = StaffEmployee.valueOf(dalton, "dalton", "The-Big-Cheese!");
        employees.add(staff);
        //System.out.println(staff);
        employees.viewEmployees();
        
       // employees.add(claimPaid);
        
    }
}
