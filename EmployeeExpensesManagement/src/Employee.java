
import java.util.Objects;

/**
 * Who is the person that needs to claim money
 *
 * @author jason
 */
public class Employee {

    private int id;
    private String title;
    private String firstName;
    private String surname;
    private String jobTitle;
    private String department;

    public Employee() {
    }

    public Employee(int id, String jobTitle) {
        this.id = id;
        this.jobTitle = jobTitle;
    }

    public Employee(int id, String title, String firstName, String surname, String jobTitle, String department) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.surname = surname;
        this.jobTitle = jobTitle;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {

        if (firstName.isEmpty() || firstName.isBlank()
                || firstName.length() < 2) {
            System.out.println("ERR: Employee requires a proper first name");
        }
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     *
     * @return The name used for mailing
     */
    public String getMailingName() {
        return title + " " + firstName + " " + surname;
    }

    /**
     * Generates a name for the mailing
     *
     * @param firstInitialOnly true to include the first initial; false to
     * exclude first initial
     * @return A titled name for sending out mail, with or without the first
     * initial based on the choice.
     */
    public String getMailingName(boolean firstInitialOnly) {
        return firstInitialOnly ? title + " " + firstName.substring(0, 1)
                + ". " + surname : title + " " + surname;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("Employee ").append(id).append(": ")
                .append(title).append(" ").append(firstName).append(" ").append(surname)
                .append(" (");
        
        if (jobTitle != null) {
            sb.append(jobTitle);
        }
        
        sb.append(") ");
        
        if (department != null) {
            sb.append("works in the ").append(department).append(" department");
        }
        return sb.toString();
    }

}
