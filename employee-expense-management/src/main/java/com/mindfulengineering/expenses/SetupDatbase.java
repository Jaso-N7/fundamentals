package com.mindfulengineering.expenses;

import java.sql.*;

/**
 *
 * @author jason
 */
public class SetupDatbase {
    
    private final static String h2db = "jdbc:h2:./expenses";
    private final static String CREATE_QUERY = """
                                               CREATE TABLE employees (id INTEGER,
                                                   title VARCHAR(5),
                                                   firstName VARCHAR(255),
                                                   surname VARCHAR(255),
                                                   jobTitle VARCHAR(255),
                                                   department VARCHAR(50),
                                               PRIMARY KEY (id))
                                               """;
    
    public static void main(String[] args) 
            throws ClassNotFoundException {
        
        Class.forName("org.h2.Driver");
        try (Connection cxn = DriverManager.getConnection(h2db, "sa", "")) {

            Statement stm = cxn.createStatement();
            stm.executeUpdate(CREATE_QUERY);

        } catch (SQLException sqlx) {
            System.out.println("Unable to connect to the expenses db");
        }
        
    }
}
