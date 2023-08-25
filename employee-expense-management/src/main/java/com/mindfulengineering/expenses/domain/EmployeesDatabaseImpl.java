package com.mindfulengineering.expenses.domain;

import com.mindfulengineering.expenses.exceptions.EmployeeNotFoundException;
import java.sql.*;
import java.time.ZonedDateTime;
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

        String insert
                = "INSERT INTO employees (id ,title, firstName, surname, jobTitle, department)";

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

        try {
            return findById(id) instanceof Employee;
        } catch (EmployeeNotFoundException ex) {
            return false;
        }
    }

    @Override
    public Employee findById(Integer employeeId) throws EmployeeNotFoundException {

        try (Connection cn = DriverManager.getConnection(h2db, "sa", "")) {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM employees WHERE id = "
                    + employeeId);

            if (rs.next()) {
                return new Employee(rs.getInt("id"), rs.getString("title"), rs.getString("firstName"),
                        rs.getString("surname"), rs.getString("jobTitle"),
                        Department.valueOf(rs.getString("department").toUpperCase()));
            }

        } catch (SQLException sqlx) {
            throw new EmployeeNotFoundException();
        }

        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee findBySurname(String surname) throws NullPointerException {

        try (Connection cn = DriverManager.getConnection(h2db, "sa", "")) {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM employees WHERE surname like '%" 
                    + surname + "%'");

            if (rs.next()) {
                return new Employee(rs.getInt("id"), rs.getString("title"), 
                        rs.getString("firstName"), rs.getString("surname"), 
                        rs.getString("jobTitle"),
                        Department.valueOf(rs.getString("department").toUpperCase()));
            }

        } catch (SQLException sqlx) {
            throw new NullPointerException();
        }

        throw new NullPointerException();
    }

    @Override
    public List<Employee> getEmployees() {
        
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

        return el;
    }

    @Override
    public void viewEmployees() {

        for (Employee e : getEmployees()) {
            System.out.println(e);

            for (ExpenseClaim ec : getClaims(e.getId())) {
                System.out.println(ec);
                viewExpenseItems(ec.getId());
                System.out.println("Total value of claim " + ec.getTotalAmount());
            }
        }

    }
    
    
    // !!! TODO Complete the implementation
    private List<ExpenseItem> getExpenseItemsForClaim(int claimId) {
        List<ExpenseItem> items = new LinkedList<>();
        
        return items;
    }
    
    private List<ExpenseClaim> getClaims (int employeeId) {
        
        List<ExpenseClaim> claims = new LinkedList<>();
        try (Connection c = DriverManager.getConnection(h2db, "sa", "")) {
        
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM expenseclaims WHERE employeeId = " + employeeId);
            
            while (rs.next()) {
                claims.add(
                        new ExpenseClaim.Builder(rs.getInt("id"),
                                rs.getInt("employeeId"),
                                ZonedDateTime.parse(rs.getString("dateOfClaim")))
                                //.expense(getExpenseItemsForClaim())
                                .approved(rs.getBoolean("approved"))
                                .paid(rs.getBoolean("paid"))
                                .build());
            }
            
            return claims;
            
        } catch (SQLException sx) {
            System.out.println("Unable to retrieve claims");
        }
        
        return claims;
    }
    
    private void viewExpenseItems(int claimId) {
            
        try (Connection c = DriverManager.getConnection(h2db, "sa", "")) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM expenseitems WHERE claimId = " + claimId);
            
            while (rs.next()) {
                System.out.println(String.format("ExpenseItem{id=%d, claimId=%d, expenseType=%s, description=%s, amount=$%.2f}",
                        rs.getInt("id"), rs.getInt("claimId"), rs.getString("expenseType"),
                        rs.getString("description"), rs.getDouble("amount")));
            }
        } catch (SQLException x) {
            System.out.println("Unable to retrieve ExpenseItems");
        }
    }


}
