package com.mindfulengineering.expenses;

import com.mindfulengineering.expenses.domain.Employees;
import com.mindfulengineering.expenses.domain.ExpenseClaim;
import com.mindfulengineering.expenses.exceptions.EmployeeNotFoundException;
import com.mindfulengineering.expenses.processing.ExpenseManagementProcess;
import com.mindfulengineering.expenses.processing.ExpressExpenseManagementProcess;
import com.mindfulengineering.expenses.processing.RegularExpenseManagementProcess;
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
                          a - approve a claim
                          f - express claim appoval
                          p - print all employees
                          x - exit""";

    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Employees employees = new Employees();
        UIFunctions ui = new UIFunctions();

        ExpenseManagementProcess regularProcess = RegularExpenseManagementProcess.create();
        ExpenseManagementProcess expressProcess = ExpressExpenseManagementProcess.create();

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

                        int regId = regularProcess.registerExpenseClaim(claim);
                        expressProcess.registerExpenseClaim(claim);
                        System.out.println("Claim id " + regId);

                    } catch (EmployeeNotFoundException ex) {
                        System.out.println("There was no employee with ID " + claim.getEmployeeId());
                    }
                }

                case 'a' -> {
                    System.out.println("Enter the claim Id");
                    int cid = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter approver Id");
                    int eid = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        regularProcess.approveClaim(cid, employees.findById(eid));
                    } catch (EmployeeNotFoundException enf) {
                        System.out.println("Invalid approver id");
                    }
                }
                case 'f' -> {
             
                    System.out.println("Enter approver Id");
                    int eid = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        expressProcess.approveClaim(-1, employees.findById(eid));
                    } catch (EmployeeNotFoundException enf) {
                        System.out.println("Invalid approver id");
                    }
                }
                case 'p' ->
                    employees.viewEmployees();
                case 'x' -> {
                    break;
                }
                default ->
                    System.out.println("Invalid choice");
            }

        } while (choice != 'x');

    }
}
