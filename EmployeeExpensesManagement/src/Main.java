import java.time.ZonedDateTime;
import java.time.ZoneId;

/**
 *
 * @author jason
 */
public class Main {
    public static void main(String[] args) {
        
        Employee mac = new Employee();
        mac.setId(1);
        mac.setTitle("Mr.");
        mac.setFirstName("Angus");
        mac.setSurname("MacGuyver");
        
        System.out.println(mac.getMailingName());
        System.out.println(mac.getMailingName(true));
        System.out.println(mac.getMailingName(false));
        
        Employee dalton = new Employee(2, "Manager");
        dalton.setFirstName("Johnny");
        dalton.setSurname("Dalton");
        
        Department itDept = new Department("IT", dalton);
        System.out.println(itDept.getName() + " managed by " 
                + itDept.getManagerName().getSurname());
        
        DepartmentAlt hr = new DepartmentAlt("Hardly Relevant", dalton);
        System.out.println(hr.name() + " managed by " 
                + hr.managerName().getSurname());
        
        // Testing expense claims
        ExpenseClaim expenseClaim = 
                new ExpenseClaim.Builder(1_001, mac.getId(),
                        ZonedDateTime.now(ZoneId.of("Jamaica")), 5_153.58)
                .build();
        System.out.println("New expense claim for employee ID: " + expenseClaim.getId());
        System.out.println("Attempting to pay for the expense:");
        ExpenseClaim claimNotApproved = expenseClaim.paid(true);
        System.out.println("Checking paid status: " + claimNotApproved.isPaid());
        System.out.println("Approving claim...");
        ExpenseClaim claimApproved = claimNotApproved.approved(true);
        System.out.println("Paying for claim...");
        ExpenseClaim claimPaid = claimApproved.paid(true);
        System.out.println("Paid: " + claimPaid.isPaid());
        System.out.println(claimPaid);
        
        // Testing expense items
        ExpenseItem expenseItem = ExpenseItem.valueOf(8_000, claimPaid.getId(), 
                "hotel", "Rio Grande Ocho Rios, 2 nights", 75_156.65);
        System.out.println(expenseItem.getDescription());
        
    }
}
