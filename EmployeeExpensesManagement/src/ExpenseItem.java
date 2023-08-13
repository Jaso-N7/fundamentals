/**
 * An ExpenseItem is a Class of:
 * 
 * ExpenseItem ei = ExpenseItem.valueOf(int, int String, String, double)
 * 
 * INTERPRETATION: What the money is used for (hotel / food / travel)
 * 
 * @author jason
 */
public class ExpenseItem {
    
    private final int id;
    private final int claimId;
    private String expenseType;
    private String description;
    private double amount;

    private ExpenseItem(int id, int claimId, String expenseType, 
            String description, double amount) {
        this.id = id;
        this.claimId = claimId;
        this.expenseType = expenseType;
        this.description = description;
        this.amount = amount;
    }
    
    public static ExpenseItem valueOf(int id, int claimId, String expenseType, 
            String description, double amount) {
        return new ExpenseItem(id, claimId, expenseType, description, amount);
    }

    public int getId() {
        return id;
    }

    public int getClaimId() {
        return claimId;
    }

    public String getExpenseType() {
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
            
}
