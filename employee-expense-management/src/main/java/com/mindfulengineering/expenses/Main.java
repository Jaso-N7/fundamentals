package com.mindfulengineering.expenses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindfulengineering.expenses.domain.*;
import com.mindfulengineering.expenses.exceptions.EmployeeNotFoundException;
import java.sql.*;
import java.time.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jason
 */
public class Main {
    public static void main(String[] args) 
            throws JsonProcessingException, 
            ClassNotFoundException, 
            SQLException {
        
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
                ( employees.employeeExists(mac.getId()) ? "Yes" : "No" ));
        
        String james = employees.employeeExists(6_000) ? "Yes" : "No";
        System.out.println("Does James exist? " + james);
        System.out.println("");
        System.out.println(employees);
        
        // Testing expense claims
        System.out.println("\nTesting Expense Claims ---");
        ExpenseClaim expenseClaim = 
                new ExpenseClaim.Builder(1_001, mac.getId(),
                        ZonedDateTime.now(ZoneId.of("Jamaica")))
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
        
        System.out.println(mac);
        
        ObjectMapper objMapper = new ObjectMapper();
        String employeeToJSON = objMapper.writeValueAsString(mac);
        System.out.println(employeeToJSON);
        
        Employee employeeFromJSON = objMapper.readValue(employeeToJSON, Employee.class);
        System.out.println(employeeFromJSON);
        
        Class.forName("org.h2.Driver");
        Connection cnx = DriverManager.getConnection("jdbc:h2:./customerdata",
                "sa", "");
        Statement stm = cnx.createStatement();
        // Cannot run multiple times, as the table and data will already exist
        //stm.executeUpdate("CREATE TABLE customer (id INTEGER, name VARCHAR(255), age INTEGER, PRIMARY KEY(id))");
        //stm.executeUpdate("INSERT INTO customer (id, name, age) VALUES (1, 'Matt', 21");
        ResultSet rs = stm.executeQuery("SELECT * FROM customer");
        
        while (rs.next()) {            
            System.out.println(rs.getInt("id"));
            System.out.println(rs.getString("name"));
        }
        cnx.close();
    }
}
