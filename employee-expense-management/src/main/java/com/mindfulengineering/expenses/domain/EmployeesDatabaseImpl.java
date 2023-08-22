package com.mindfulengineering.expenses.domain;

import com.mindfulengineering.expenses.exceptions.EmployeeNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EmployeesDatabaseImpl implements Employees {

    private final String h2db;
    
    public EmployeesDatabaseImpl() throws ClassNotFoundException {
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
