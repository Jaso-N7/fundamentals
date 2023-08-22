package com.mindfulengineering.expenses;

import java.sql.*;
import java.util.List;

/**
 *
 * @author jason
 */
public class SetupDatbase {

    private final static String h2db = "jdbc:h2:./expenses";
    private final static List<String> createTables
            = List.of("""
                    CREATE TABLE employees (id INTEGER,
                        title VARCHAR(5),
                        firstName VARCHAR(255),
                        surname VARCHAR(255),
                        jobTitle VARCHAR(255),
                        department VARCHAR(50),
                    PRIMARY KEY (id))
                    """,
                    """
                    CREATE TABLE expenseclaims (id INTEGER,
                    employeeId INTEGER,
                    dateOfClaim VARCHAR(255),
                    approved TINYINT,
                    paid TINYINT,
                    PRIMARY KEY (id) )
                    """,
                    """
                    CREATE TABLE expenseitems (id INTEGER,
                    claimId INTEGER,
                    expenseType VARCHAR(255),
                    description VARCHAR(255),
                    amount DOUBLE,
                    PRIMARY KEY (id) )
                    """);

    public static void main(String[] args)
            throws ClassNotFoundException {

        Class.forName("org.h2.Driver");
        try (Connection cxn = DriverManager.getConnection(h2db, "sa", "")) {

            Statement stm = cxn.createStatement();
            for (String table : createTables) {
                stm.executeUpdate(table);
            }

        } catch (SQLException sqlx) {
            System.out.println("Unable to connect to the expenses db");
        }

    }
}
