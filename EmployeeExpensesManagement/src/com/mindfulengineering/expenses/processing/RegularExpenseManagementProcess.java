package com.mindfulengineering.expenses.processing;

import com.mindfulengineering.expenses.domain.Employee;
import com.mindfulengineering.expenses.domain.ExpenseClaim;
import com.mindfulengineering.expenses.domain.StaffEmployee;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jason
 */
public final class RegularExpenseManagementProcess 
        implements ExpenseManagementProcess {

    private final List<ExpenseClaim> claims;
        
    private RegularExpenseManagementProcess() {
        claims = new LinkedList<>();
    }
    
    public static RegularExpenseManagementProcess create () {
        return new RegularExpenseManagementProcess();
    }
        
    /**
     * Register the ExpenseClaim and returns a unique ID for each claim
     * @param c - the ExpenseClaim to be registered
     * @return An unique ID for the recently registered claim
     */
    @Override
    public int registerExpenseClaim(ExpenseClaim c) {
        claims.add(c);
        return claims.indexOf(c);
    }

    @Override
    public boolean approveClaim(int id, Employee approver) {
        ExpenseClaim c = claims.get(id);
        double amount = c.getTotalAmount();
        
        if (amount < 100) {
            return true;
        } else return amount > 100 && approver instanceof StaffEmployee;
    }
    
}
