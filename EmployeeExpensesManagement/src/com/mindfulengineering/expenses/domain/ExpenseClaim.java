package com.mindfulengineering.expenses.domain;


import java.time.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * An ExpenseClaim is a Class of:
 * 
 * ExpenseClaim ec = new ExpenseClaim.Builder(ExpenseClaimId, EmployeeID, ZonedDateTime)
 *                   .expense(ExpenseItem)
 *                   .approved(Boolean)
 *                   .paid(Boolean)
 *                   .build()
 * 
 * INTERPRETATION: The date of each claim and the reason (the expense item)
 * WHERE: expense, approved and paid are optional
 *
 * @author jason
 */
public class ExpenseClaim {

    private final int id;
    private final int employeeId;
    private final ZonedDateTime dateOfClaim;
    private final List<ExpenseItem> expenseItems;
    private final boolean approved;
    private final boolean paid;

    public int getId() {
        return id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public ZonedDateTime getDateOfClaim() {
        return dateOfClaim;
    }

    public double getTotalAmount() {
        double totalAmount = 0.0D;
        
        for (ExpenseItem i : expenseItems) {
            totalAmount += i.getAmount();
        }
        
        return totalAmount;
    }

    /**
     * Prints out the ExpenseItems to the console
     */
    public void viewExpenseItems() {
        StringBuilder sb = new StringBuilder();
        for (ExpenseItem ei : expenseItems) {
            sb.append(ei).append('\n');
        }
        
        System.out.println(sb);
    }
    
    public boolean isApproved() {
        return approved;
    }

    public boolean isPaid() {
        return paid;
    }
    
    /**
     * Sets the approved status
     * @param isApproved true if approved; false otherwise
     * @return A new ExpenseClaim instance with the approved status set
     */
    public ExpenseClaim approved(boolean isApproved){
        
        return new ExpenseClaim.Builder(id, employeeId, dateOfClaim)
                .expense(expenseItems)
                .approved(isApproved)
                .paid(this.paid)
                .build();
    }
    
    /**
     * Sets the paid status
     * @param isPaid set to true if it is paid; otherwise false
     * @return A new ExpenseClaim object with the paid status set.
     */
    public ExpenseClaim paid(boolean isPaid) {
        return new ExpenseClaim.Builder(id, employeeId, dateOfClaim)
                .expense(expenseItems)
                .approved(this.approved)
                .paid(isPaid)
                .build();
    }
    
    public static class Builder {

        // Required parameters
        private final int id;
        private final int employeeId;
        private final ZonedDateTime dateOfClaim;

        // Optional parameters - initialized to default
        private List<ExpenseItem> expenseItems = new LinkedList<>();
        private boolean approved = false;
        private boolean paid = false;

        public Builder(int id, int employeeId,
                ZonedDateTime dateOfClaim) {
            this.id = id;
            this.employeeId = employeeId;
            this.dateOfClaim = dateOfClaim;
        }

        public Builder approved(boolean isApproved) {
            approved = isApproved;
            return this;
        }

        public Builder paid(boolean isPaid) {
            
            if(approved && isPaid) {
                paid = isPaid;
            } else {
                System.out.println("This item cannot be paid as it has not yet been approved.");
            }
           return this;
        }
        
        /**
         * Add an ExpenseItem to the ExpenseClaim
         * 
         * @param item
         * @return A Builder object
         */
        public Builder expense(ExpenseItem item) {
            expenseItems.add(item);
            return this;
        }
        
        public Builder expense(List<ExpenseItem> items) {
            expenseItems = new LinkedList<>(items);
            return this;
        }

        public ExpenseClaim build() {
            return new ExpenseClaim(this);
        }
    }

    private ExpenseClaim(Builder builder) {
        id = builder.id;
        employeeId = builder.employeeId;
        dateOfClaim = builder.dateOfClaim;
        approved = builder.approved;
        paid = builder.paid;
        expenseItems = builder.expenseItems;
    }

    @Override
    public String toString() {
        return String
                .format("ExpenseClaim %d for employee %d, dated %s, %s & %s%n",
                        id, employeeId, dateOfClaim.toLocalDate(), 
                        (approved ? "has been approved" : "has not been approved"), 
                        (paid ? "has been paid": "has not been paid"));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.id;
        hash = 19 * hash + this.employeeId;
        hash = 19 * hash + Objects.hashCode(this.dateOfClaim);
        hash = 19 * hash + Objects.hashCode(this.expenseItems);
        hash = 19 * hash + (this.approved ? 1 : 0);
        hash = 19 * hash + (this.paid ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExpenseClaim other = (ExpenseClaim) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.employeeId != other.employeeId) {
            return false;
        }
        if (this.approved != other.approved) {
            return false;
        }
        if (this.paid != other.paid) {
            return false;
        }
        if (!Objects.equals(this.dateOfClaim, other.dateOfClaim)) {
            return false;
        }
        return Objects.equals(this.expenseItems, other.expenseItems);
    }

    
    
}
