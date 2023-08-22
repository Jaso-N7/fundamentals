package com.mindfulengineering.expenses.utilities;

import java.time.LocalDate;

/**
 *
 * @author jason
 */
public interface ExpenseAnalysis {
    void printOutstandingExpenseClaims();
    void printPaidExpenseClaims(LocalDate start, LocalDate end);
    void printExpenseClaims(double aboveAmount);
}
