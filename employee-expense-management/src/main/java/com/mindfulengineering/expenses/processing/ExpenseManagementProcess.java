package com.mindfulengineering.expenses.processing;

import com.mindfulengineering.expenses.domain.Employee;
import com.mindfulengineering.expenses.domain.ExpenseClaim;

/**
 *
 * @author jason
 */
public interface ExpenseManagementProcess {
    
    int registerExpenseClaim(ExpenseClaim c);
    boolean approveClaim(int id, Employee approver);
    
}
