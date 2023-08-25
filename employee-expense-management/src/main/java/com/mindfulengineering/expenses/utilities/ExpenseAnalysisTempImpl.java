package com.mindfulengineering.expenses.utilities;

import com.mindfulengineering.expenses.domain.Employees;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jason
 */
public class ExpenseAnalysisTempImpl implements ExpenseAnalysis {

    private final Employees employees;

    public ExpenseAnalysisTempImpl(Employees employees) {
        this.employees = employees;
    }

    /**
     * Print all outstanding expense claims that have not been approved
     */
    @Override
    public void printOutstandingExpenseClaims() {
        employees.getEmployees().stream()
                .forEach(e
                        -> e.getClaims().values().stream()
                        .filter(c -> !c.isApproved())
                        .forEach(System.out::println)
                );
    }

    /**
     * Print employees that have paid ExpenseClaims between the start and end
     * date
     *
     * @param start Date from which to include paid ExpenseClaims
     * @param end Include ExpenseClaims up to this date
     */
    @Override
    public void printPaidExpenseClaims(LocalDate start, LocalDate end) {
        employees.getEmployees().stream()
                .forEach(e
                        -> e.getClaims().values().stream()
                        .filter(c -> c.isPaid())
                        .filter(paid -> {
                            var ld = LocalDate.from(paid.getDateOfClaim());
                            return ld.isAfter(start) && ld.isBefore(end);
                        })
                        .forEach(System.out::println)
                );
    }

    /**
     * Print all ExpenseClaims that have their total above the specified amount
     *
     * @param aboveAmount Filter only for ExpenseClaims that are above this
     * amount
     */
    @Override
    public void printExpenseClaims(double aboveAmount) {
        employees.getEmployees().stream()
                .forEach(e
                        -> e.getClaims().values().stream()
                        .filter(c -> c.getTotalAmount() >= aboveAmount)
                        .forEach(System.out::println)
                );
    }

    /**
     * Write / Export all outstanding expense claims to the given location
     *
     * @param toLocation Where to export or save the data to
     */
    @Override
    public void writeOutstandingExpenseClaims(String toLocation) {
        String homeDir = System.getProperty("user.dir");
        String slash = File.separator;
        String newLine = System.getProperty("line.separator");

        Path currDir = Paths.get(homeDir);
        System.out.println(Files.isDirectory(currDir));

        // Creating and writing to a file
        Path report = Paths.get(homeDir + slash + toLocation
                + "outstanding-claims-report.txt");

        employees.getEmployees().stream()
                .forEach(e
                        -> e.getClaims().values().stream()
                        .filter(c -> !c.isApproved())
                        .forEach(c
                                -> {
                            try {
                                Files.writeString(report, c + newLine);
                            } catch (IOException ex) {
                                System.out.println("Unable to save the report");
                            }
                        })
                );
        System.out.println("Report saved to " + report);
    }

    /**
     * Write / Export all paid expense claims to the given location
     *
     * @param toLocation Where to export or save the data to
     * @param start After this date
     * @param end Before this date
     */
    @Override
    public void writePaidExpenseClaims(String toLocation, LocalDate start, LocalDate end) {

        String homeDir = System.getProperty("user.dir");
        String slash = File.separator;
        String newLine = System.getProperty("line.separator");

        Path currDir = Paths.get(homeDir);
        System.out.println(Files.isDirectory(currDir));

        // Creating and writing to a file
        Path report = Paths.get(homeDir + slash + toLocation + slash
                + "paid-claims-report-" + start + "-" + end + ".txt");

        employees.getEmployees().stream()
                .forEach(e
                        -> e.getClaims().values().stream()
                        .filter(c -> c.isPaid())
                        .filter(paid -> {
                            var ld = LocalDate.from(paid.getDateOfClaim());
                            return ld.isAfter(start) && ld.isBefore(end);
                        })
                        .forEach(c -> {
                            try {
                                Files.writeString(report, c + newLine);
                            } catch (IOException ex) {
                                System.out.println("Unable to save the report");
                            }
                        })
                );

        System.out.println("Report saved to " + report);
    }

    /**
     * Write / Export all expense claims that are above the specified amount to
     * the given location
     *
     * @param toLocation Where to export or save the data to
     * @param aboveAmount Minimum amount on expense claims
     */
    @Override
    public void writeExpenseClaims(String toLocation, double aboveAmount) {

        String homeDir = System.getProperty("user.dir");
        String slash = File.separator;
        String newLine = System.getProperty("line.separator");

        Path currDir = Paths.get(homeDir);
        System.out.println(Files.isDirectory(currDir));

        // Creating and writing to a file
        Path report = Paths.get(homeDir + slash + toLocation + slash
                + "claims-above-" + aboveAmount + "-report.txt");

        employees.getEmployees().stream()
                .forEach(e
                        -> e.getClaims().values().stream()
                        .filter(c -> c.getTotalAmount() >= aboveAmount)
                        .forEach(c -> {
                            try {
                                Files.writeString(report, c + newLine);
                            } catch (IOException ex) {
                                System.out.println("Could not save claims report");
                            }
                        })
                );
        System.out.println("Report saved to " + report);
    }

}
