package com.mindfulengineering.expenses;

import com.mindfulengineering.expenses.domain.Employee;
import com.mindfulengineering.expenses.domain.Employees;
import com.mindfulengineering.expenses.domain.ExpenseClaim;
import com.mindfulengineering.expenses.exceptions.EmployeeNotFoundException;
import com.mindfulengineering.expenses.processing.ExpenseManagementProcess;
import com.mindfulengineering.expenses.processing.ExpressExpenseManagementProcess;
import com.mindfulengineering.expenses.processing.RegularExpenseManagementProcess;
import com.mindfulengineering.expenses.ui.UIFunctions;
import com.mindfulengineering.expenses.utilities.ExpenseAnalysis;
import com.mindfulengineering.expenses.utilities.ExpenseAnalysisTempImpl;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                          p - print all employees
                          r1 - outstanding expense claims
                          r2 - paid expense claims
                          r3 - expense claims above specified amount
                          x - exit""";
    
    private final static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        Employees employees = new Employees();
        UIFunctions ui = new UIFunctions();
        ExpenseAnalysis analyse = new ExpenseAnalysisTempImpl();
        
        ExpenseManagementProcess regularProcess = RegularExpenseManagementProcess.create();
        ExpenseManagementProcess expressProcess = ExpressExpenseManagementProcess.create();
        
        String choice;
        do {
            System.out.println(MENU);
            choice = scanner.nextLine().toLowerCase();
            
            switch (choice) {
                case "e" -> {
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
                
                case "c" -> {
                    ExpenseClaim claim = ui.registerNewExpenseClaim();
                    try {
                        employees.add(claim);
                        
                        int regId = regularProcess.registerExpenseClaim(claim);
                        expressProcess.registerExpenseClaim(claim);
                        System.out.println("The claim has been registered with ID "
                                + regId);
                        
                    } catch (EmployeeNotFoundException ex) {
                        System.out.println("There was no employee with ID " + claim.getEmployeeId());
                    }
                }
                
                case "a" -> {
                    System.out.println("Enter the claim Id");
                    int cid = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.println("Enter employee Id");
                    int eid = scanner.nextInt();
                    scanner.nextLine();
                    
                    try {
                        Employee foundEmployee = employees.findById(eid);
                        
                        System.out.println("r - regular approval\ne - express approval");
                        String claimType = scanner.nextLine();
                        
                        ExpenseManagementProcess process
                                = claimType.toLowerCase().equals("r")
                                ? regularProcess : expressProcess;
                        
                        if (process.approveClaim(cid, foundEmployee)) {
                            System.out.println("Claim was approved");
                        } else {
                            System.out.println("Claim was not approved");
                        }
                    } catch (EmployeeNotFoundException enf) {
                        System.out.println("Invalid approver id");
                    }
                }
                
                case "p" ->
                    employees.viewEmployees();
                case "r1" ->
                    analyse.printOutstandingExpenseClaims();
                case "r2" -> {
                    System.out.println("Enter date from (yyyy-mm-dd)");
                    String from = scanner.nextLine();
                    LocalDate fromLD = LocalDate.parse(from, DateTimeFormatter.ISO_DATE);
                    
                    System.out.println("Enter date to (yyyy-mm-dd)");
                    String to = scanner.nextLine();
                    LocalDate toLD = LocalDate.parse(to, DateTimeFormatter.ISO_DATE);
                    
                    analyse.printPaidExpenseClaims(fromLD, toLD);
                }
                case "r3" -> {
                    
                    System.out.println("Ente amount");
                    double filter = scanner.nextDouble();
                    scanner.nextLine();
                    
                    analyse.printExpenseClaims(filter);
                }
                
                default ->
                    System.out.println("Invalid choice");
            }
            
        } while (!choice.equals("x"));
        
    }
}
