package com.mindfulengineering.expenses;

import com.mindfulengineering.expenses.domain.Employees;
import com.mindfulengineering.expenses.ui.UIFunctions;
import java.util.Scanner;

/**
 *
 * @author jason
 */
public class ExpenseManagementSystem {
    
    private final static String MENU = """
                          Expense Management System
                          -------------------------
                          e - register new employee
                          c - register new claim
                          p - print all employees
                          x - exit""";
    
    private final static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {

        Employees employees = new Employees(10);
        UIFunctions ui = new UIFunctions();
        
        char choice;
        do {
            System.out.println(MENU);
            choice = scanner.nextLine().toLowerCase().charAt(0);

            switch (choice) {
                case 'e' ->
                    employees.add(ui.registerNewEmployee());
                case 'c' -> employees.add(ui.registerNewExpenseClaim());
                case 'p' -> employees.viewEmployees();
                case 'x' -> {
                    break;
                }
                default ->
                    System.out.println("Invalid choice");

            }
        } while (choice != 'x');

    }
}
