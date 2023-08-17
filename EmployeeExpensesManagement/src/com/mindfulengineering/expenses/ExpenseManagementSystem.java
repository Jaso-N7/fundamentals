package com.mindfulengineering.expenses;

import com.mindfulengineering.expenses.domain.Employees;
import com.mindfulengineering.expenses.domain.ExpenseClaim;
import com.mindfulengineering.expenses.exceptions.EmployeeNotFoundException;
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

        Employees employees = new Employees();
        UIFunctions ui = new UIFunctions();

        char choice;
        do {
            System.out.println(MENU);
            choice = scanner.nextLine().toLowerCase().charAt(0);

            switch (choice) {
                case 'e' -> {
                    boolean keepAsking = true;
                    while (keepAsking) {
                        System.out.println("Is this a staff member? (y / n)");
                        char ynp = scanner.nextLine().toLowerCase().charAt(0);

                        switch (ynp) {
                            case 'y' -> {
                                employees.add(ui.registerNewEmployee(true));
                                keepAsking = false;
                            }
                            case 'n' -> {
                                employees.add(ui.registerNewEmployee(false));
                                keepAsking = false;
                            }
                            default -> {
                                System.out.println("Invalid option, enter either 'Y' or 'N'");
                            }
                        } 
                    }
                }

                case 'c' -> {
                    ExpenseClaim claim = ui.registerNewExpenseClaim();
                    try {
                        employees.add(claim);
                    } catch (EmployeeNotFoundException ex) {
                        System.out.println("There was no employee with ID " + claim.getEmployeeId());
                    }
                }

                case 'p' -> employees.viewEmployees();
                case 'x' -> { break; }
                default -> System.out.println("Invalid choice");
            }

        } while (choice != 'x');

    }
}
