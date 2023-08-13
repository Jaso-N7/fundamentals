package com.mindfulengineering.expenses.domain;

import java.util.Objects;

/**
 * An ExpenseItem is a Class of:
 
 ExpenseItem ei = ExpenseItem.create(int, int String, String, double)
 
 INTERPRETATION: What the money is used for (hotel / food / travel)
 * 
 * @author jason
 */
public class ExpenseItem {
    
    private final int id;
    private final int claimId;
    private final ExpenseType expenseType;
    private final String description;
    private final double amount;

    private ExpenseItem(int id, int claimId, ExpenseType expenseType, 
            String description, double amount) {
        this.id = id;
        this.claimId = claimId;
        this.expenseType = expenseType;
        this.description = description;
        this.amount = amount;
    }
    
    public static ExpenseItem create(int id, int claimId, ExpenseType expenseType, 
            String description, double amount) {
        return new ExpenseItem(id, claimId, expenseType, description, amount);
    }

    public int getId() {
        return id;
    }

    public int getClaimId() {
        return claimId;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ExpenseItem{");
        sb.append("id=").append(id);
        sb.append(", claimId=").append(claimId);
        sb.append(", expenseType=").append(expenseType);
        sb.append(", description=").append(description);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        hash = 97 * hash + this.claimId;
        hash = 97 * hash + Objects.hashCode(this.expenseType);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.amount) ^ (Double.doubleToLongBits(this.amount) >>> 32));
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
        final ExpenseItem other = (ExpenseItem) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.claimId != other.claimId) {
            return false;
        }
        if (Double.doubleToLongBits(this.amount) != Double.doubleToLongBits(other.amount)) {
            return false;
        }
        if (!Objects.equals(this.expenseType, other.expenseType)) {
            return false;
        }
        return Objects.equals(this.description, other.description);
    }
    
}
