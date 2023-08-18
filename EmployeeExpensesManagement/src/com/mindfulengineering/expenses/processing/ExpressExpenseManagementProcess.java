package com.mindfulengineering.expenses.processing;

import com.mindfulengineering.expenses.domain.Employee;
import com.mindfulengineering.expenses.domain.ExpenseClaim;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jason
 */
public final class ExpressExpenseManagementProcess 
        implements ExpenseManagementProcess {

    private final List<ExpenseClaim> claims;

    private ExpressExpenseManagementProcess() {
        this.claims = new LinkedList<>();
    }
    
    public ExpressExpenseManagementProcess create () {
        return new ExpressExpenseManagementProcess();
    }
    
    @Override
    public int registerExpenseClaim(ExpenseClaim c) {
        return -1;
    }

    @Override
    public boolean approveClaim(int id, Employee approver) {
        
        ExpenseClaim c = claims.get(id);
        double amount = c.getTotalAmount();
        
        if (amount < 500) {
            return true;
        }
        
        return false;
    }
    
}
