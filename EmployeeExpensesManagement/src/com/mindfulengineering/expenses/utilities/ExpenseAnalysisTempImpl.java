package com.mindfulengineering.expenses.utilities;

import com.mindfulengineering.expenses.domain.Employees;
import com.mindfulengineering.expenses.domain.ExpenseClaim;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author jason
 */
public class ExpenseAnalysisTempImpl implements ExpenseAnalysis {

    private Employees employees;

    public ExpenseAnalysisTempImpl(Employees employees) {
        this.employees = employees;
    }

    /**
     * Print all outstanding expense claims that have not been approved
     */
    @Override
    public void printOutstandingExpenseClaims() {
        employees.getEmployees().stream()
                .forEach(e -> 
                        e.getClaims().values().stream()
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
                .forEach(e -> 
                    e.getClaims().values().stream()
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
     * @param aboveAmount Filter only for ExpenseClaims that are above this amount
     */
    @Override
    public void printExpenseClaims(double aboveAmount) {
        employees.getEmployees().stream()
                .forEach(e -> 
                    e.getClaims().values().stream()
                            .filter(c -> c.getTotalAmount() >= aboveAmount)
                            .forEach(System.out::println)
                            );
    }

}
