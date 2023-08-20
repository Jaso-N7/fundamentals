package com.mindfulengineering.expenses.utilities;

import java.time.LocalDate;

/**
 *
 * @author jason
 */
public class ExpenseAnalysisTempImpl implements ExpenseAnalysis {

    @Override
    public void printOutstandingExpenseClaims() {
        System.out.println("this feature is not currently available");
    }

    @Override
    public void printPaidExpenseClaims(LocalDate start, LocalDate end) {
        System.out.println("this feature is not currently available");
    }

    @Override
    public void printExpenseClaims(double aboveAmount) {
        System.out.println("this feature is not currently available");
    }
    
}
