package com.mindfulengineering.expenses.domain;


import java.time.*;
import java.util.Objects;

/**
 * An ExpenseClaim is a Class of:
 * 
 * ExpenseClaim ec = new ExpenseClaim.Builder(int, int, ZonedDateTime, double)
 *                   .approved(Boolean)
 *                   .paid(Boolean)
 *                   .build()
 * 
 * INTERPRETATION: The date of each claim, amount spent, and reason
 * WHERE: approved and paid are optional
 *
 * @author jason
 */
public class ExpenseClaim {

    private final int id;
    private final int employeeId;
    private final ZonedDateTime dateOfClaim;
    private final double totalAmount;
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
        return totalAmount;
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
        
        return new ExpenseClaim.Builder(id, employeeId, dateOfClaim, totalAmount)
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
        return new ExpenseClaim.Builder(id, employeeId, dateOfClaim, totalAmount)
                .approved(this.approved)
                .paid(isPaid)
                .build();
    }
    
    public static class Builder {

        // Required parameters
        private final int id;
        private final int employeeId;
        private final ZonedDateTime dateOfClaim;
        private final double totalAmount;

        // Optional parameters - initialized to default
        private boolean approved = false;
        private boolean paid = false;

        public Builder(int id, int employeeId,
                ZonedDateTime dateOfClaim,
                double totalAmount) {
            this.id = id;
            this.employeeId = employeeId;
            this.dateOfClaim = dateOfClaim;
            this.totalAmount = totalAmount;
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

        public ExpenseClaim build() {
            return new ExpenseClaim(this);
        }
    }

    private ExpenseClaim(Builder builder) {
        id = builder.id;
        employeeId = builder.employeeId;
        dateOfClaim = builder.dateOfClaim;
        totalAmount = builder.totalAmount;
        approved = builder.approved;
        paid = builder.paid;
    }

    @Override
    public String toString() {
        return String
                .format("ExpenseClaim %d for employee %d, dated %s, totalling $%.2f,%n%s & %s%n",
                        id, employeeId, dateOfClaim.toLocalDate(), 
                        totalAmount, 
                        (approved ? "has been approved" : "has not been approved"), 
                        (paid ? "has been paid": "has not been paid"));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        hash = 83 * hash + this.employeeId;
        hash = 83 * hash + Objects.hashCode(this.dateOfClaim);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.totalAmount) ^ (Double.doubleToLongBits(this.totalAmount) >>> 32));
        hash = 83 * hash + (this.approved ? 1 : 0);
        hash = 83 * hash + (this.paid ? 1 : 0);
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
        if (Double.doubleToLongBits(this.totalAmount) != Double.doubleToLongBits(other.totalAmount)) {
            return false;
        }
        if (this.approved != other.approved) {
            return false;
        }
        if (this.paid != other.paid) {
            return false;
        }
        return Objects.equals(this.dateOfClaim, other.dateOfClaim);
    }
    
}
