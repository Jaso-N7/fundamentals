package com.mindfulengineering.expenses.domain;

import com.mindfulengineering.expenses.exceptions.EmployeeNotFoundException;
import java.sql.*;
import java.util.*;

public class EmployeesDatabaseImpl implements Employees {

    private final String h2db;
    
    public EmployeesDatabaseImpl() 
            throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        h2db = "jdbc:h2:./expenses";
    }
    
    @Override
    public void add(Employee employee) {
        
        String insert = 
                "INSERT INTO employees (id ,title, firstName, surname, jobTitle, department)";
        
        try (Connection cxn = DriverManager.getConnection(h2db, "sa", "")) {

            Statement stm = cxn.createStatement();
            stm.executeUpdate(String.format("%s VALUES (%d , '%s', '%s', '%s', '%s', '%s')",
                    insert, employee.getId(), employee.getTitle(),
                    employee.getFirstName(), employee.getSurname(),
                    employee.getJobTitle(), employee.getDepartment().name()));

        } catch (SQLException sqlx) {
            System.out.println("There was a problem connecting to the database");
            throw new RuntimeException(sqlx);
        }
    }

    @Override
    public void add(ExpenseClaim claim) throws EmployeeNotFoundException {
        
        String insertClaim = "INSERT INTO expenseclaims (id, employeeId, dateOfClaim, approved, paid) ",
                insertItem = "INSERT INTO expenseitems (id, claimId, expenseType, description, amount) ";
                
        try (Connection cxn = DriverManager.getConnection(h2db, "sa", "")) {
            Statement stm = cxn.createStatement();
            stm.executeUpdate(String.format("%s VALUES(%d, %d, '%s', %d, %d)",
                    insertClaim,
                    claim.getId(), claim.getEmployeeId(),
                    claim.getDateOfClaim(),
                    claim.isApproved() ? 1 : 0, 
                    claim.isPaid() ? 1 : 0));
            
            for (ExpenseItem item : claim.expenseItems()) {
                stm.executeUpdate(String.format("%s VALUES (%d, %d, '%s', '%s', %f)",
                        insertItem,
                        item.getId(), item.getClaimId(), item.getExpenseType(),
                        item.getDescription(), item.getAmount()));
            }
            
        } catch (SQLException t) {
            throw new EmployeeNotFoundException();
        }
    }

    @Override
    public boolean employeeExists(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Employee findById(Integer employeeId) throws EmployeeNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Employee findBySurname(String surname) throws NullPointerException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Employee> getEmployees() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void viewEmployees() {
        
        List<Employee> el = new LinkedList<>();
        
        try (Connection cxn = DriverManager.getConnection(h2db, "sa", "")) {

            Statement stm = cxn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM employees");
            
            while (rs.next()) {
                el.add(new Employee(rs.getInt("id"),
                        rs.getString("title"), rs.getString("firstName"), 
                        rs.getString("surname"), rs.getString("jobTitle"), 
                        Department.valueOf(rs.getString("department").toUpperCase())));
            }

        } catch (SQLException sqlx) {
            System.out.println("There was a problem connecting to the database");
            throw new RuntimeException(sqlx);
        }
        
        Collections.sort(el);
        
        for (Employee e : el) {
            System.out.println(e);

            for (ExpenseClaim ec : e.getClaims().values()) {
                System.out.println(ec);
                ec.viewExpenseItems();
                System.out.println("Total value of claim " + ec.getTotalAmount());
            }
        }
        
    }
    
}
