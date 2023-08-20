package com.mindfulengineering.expenses.utilities;

import java.time.LocalDate;

/**
 *
 * @author jason
 */
public class ExpenseAnalysisTempImpl implements ExpenseAnalysis {

    @Override
    public void printOutstandingExpenseClaims() {
        throw new UnsupportedOperationException("this feature is not currently available");
    }

    @Override
    public void printPaidExpenseClaims(LocalDate start, LocalDate end) {
        throw new UnsupportedOperationException("this feature is not currently available");
    }

    @Override
    public void printExpenseClaims(double aboveAmount) {
        throw new UnsupportedOperationException("this feature is not currently available");
    }
    
}
