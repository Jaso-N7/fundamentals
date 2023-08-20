package com.mindfulengineering.expenses.domain;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Who is the person that needs to claim money
 *
 * @author jason
 */
public class Employee implements Comparable<Employee> {

    private int id;
    private String title;
    private String firstName;
    private String surname;
    private String jobTitle;
    private Department department;
    private final ArrayList<ExpenseClaim> claims = new ArrayList<>();

    public Employee() { }
    
    public Employee(int id, String jobTitle) {
        this.id = id;
        this.jobTitle = jobTitle;     
    }

    public Employee(int id, String title, String firstName, String surname,
            String jobTitle, Department department) {
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Adds a new claim to this Employee (object)
     * @param claim 
     */
    public void addClaim(ExpenseClaim claim) {
        claims.add(claim);
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

        for (var claim : claims) {
            sb.append("\n").append(claim);
        }
        
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
        hash = 41 * hash + Objects.hashCode(this.title);
        hash = 41 * hash + Objects.hashCode(this.firstName);
        hash = 41 * hash + Objects.hashCode(this.surname);
        hash = 41 * hash + Objects.hashCode(this.jobTitle);
        hash = 41 * hash + Objects.hashCode(this.department);
        hash = 41 * hash + Objects.hashCode(this.claims);
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
        final Employee other = (Employee) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.jobTitle, other.jobTitle)) {
            return false;
        }
        if (this.department != other.department) {
            return false;
        }
        return Objects.equals(this.claims, other.claims);
    }

    @Override
    public int compareTo(Employee e) {
        return Integer.valueOf(id).compareTo(e.getId());
    }
    
}
