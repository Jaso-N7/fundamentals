package com.mindfulengineering.expenses.utilities;

import java.time.LocalDate;

/**
 *
 * @author jason
 */
public interface ExpenseAnalysis {
    void printOutstandingExpenseClaims();
    void writeOutstandingExpenseClaims(String toLocation);
    void printPaidExpenseClaims(LocalDate start, LocalDate end);
    void writePaidExpenseClaims(String toLocation, LocalDate start, LocalDate end);
    void printExpenseClaims(double aboveAmount);
    void writeExpenseClaims(String toLocation, double aboveAmount);
}
