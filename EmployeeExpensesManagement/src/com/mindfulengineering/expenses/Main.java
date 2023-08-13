package com.mindfulengineering.expenses;

import com.mindfulengineering.expenses.domain.ExpenseClaim;
import com.mindfulengineering.expenses.domain.ExpenseItem;
import com.mindfulengineering.expenses.domain.Employees;
import com.mindfulengineering.expenses.domain.Employee;
import com.mindfulengineering.expenses.utilities.EmployeeUtilities;
import java.time.ZonedDateTime;
import java.time.ZoneId;

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
        
        Employees employees = new Employees(15);
        employees.addEmployee(mac);
        employees.addEmployee(dalton);
        employees.addEmployee( new Employee(3, "Mrs.", "Susan", "Brown", 
                "Director", "Information Technology") );
        
        employees.viewEmployees();
        
        System.out.println("Looking for 'Dalton' => " + employees.findBySurname("dalton"));
        
        Employee notFound = employees.findBySurname("James");
        System.out.println("Looking for 'James' => " + 
                ((notFound == null) ? "No employee found" : notFound));
        
        // Testing EmployeeUtilities.employeeExists
        System.out.println("Does Dalton exist? " + 
                ( EmployeeUtilities.employeeExists(employees, mac) ? "Yes" : "No" ));
        
        String james = EmployeeUtilities
                .employeeExists(employees, new Employee(666, "Mr.", "Chris", "James", "Staff", "Finance")) 
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
        ExpenseItem expenseItem = ExpenseItem.valueOf(8_000, claimPaid.getId(), 
                "hotel", "Rio Grande Ocho Rios, 2 nights", 75_156.65);
        System.out.println(expenseItem.getDescription());
        
        // Testing equality
        System.out.println("\nTesting Equality ---");
        System.out.println(mac.equals(dalton));
        System.out.println(mac == mac);
        System.out.println(mac.getClass());
        
    }
}
