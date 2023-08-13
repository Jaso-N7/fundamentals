package com.mindfulengineering.expenses;

import com.mindfulengineering.expenses.domain.ExpenseClaim;
import com.mindfulengineering.expenses.domain.ExpenseItem;
import com.mindfulengineering.expenses.domain.Employees;
import com.mindfulengineering.expenses.domain.Employee;
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
        System.out.println("");
        System.out.println(employees);
        
        // Testing expense claims
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
        ExpenseItem expenseItem = ExpenseItem.valueOf(8_000, claimPaid.getId(), 
                "hotel", "Rio Grande Ocho Rios, 2 nights", 75_156.65);
        System.out.println(expenseItem.getDescription());
        
    }
}
