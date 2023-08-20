package com.mindfulengineering.expenses.processing;

import com.mindfulengineering.expenses.domain.Employee;
import com.mindfulengineering.expenses.domain.ExpenseClaim;

/**
 *
 * @author jason
 */
public final class ExpressExpenseManagementProcess 
        implements ExpenseManagementProcess {

    private ExpenseClaim claim;

    private ExpressExpenseManagementProcess() {
        
    }
    
    public static ExpressExpenseManagementProcess create () {
        return new ExpressExpenseManagementProcess();
    }
    
    @Override
    public int registerExpenseClaim(ExpenseClaim c) {
        this.claim = c;
        return -1;
    }

    @Override
    public boolean approveClaim(int id, Employee approver) {
        
        return claim.getTotalAmount() < 50;
    }
    
}
