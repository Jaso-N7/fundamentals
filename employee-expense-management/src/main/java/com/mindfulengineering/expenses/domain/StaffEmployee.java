package com.mindfulengineering.expenses.domain;

import java.util.Objects;

/**
 * A StaffEmployee is a Class:
 * 
 * StaffEmployee se = StaffEmployee.valueOf(Employee, Username, Password);
 * 
 * INTERPRETATION: An employee with special access privileges.
 * WHERE: StaffEmployee.of will always return a new StaffEmployee object.
 * 
 * @author jason
 */
public final class StaffEmployee extends Employee 
        implements Comparable<Employee> {
    
    private final String username;
    private final String password;
    private final Employee staff;
    
    private StaffEmployee (Employee e, String username, String password) {
        super(e.getId(), e.getTitle(), e.getFirstName(),
                e.getSurname(), e.getJobTitle(), e.getDepartment());
        this.username = username;
        this.password = password;
        staff = e;
    }
    
    public static StaffEmployee valueOf (Employee e, String username, String password) {
        return new StaffEmployee(e, username, password);
    }
    
    public String username () { return username; }
    
    public StaffEmployee resetPassword (String password) {
        return new StaffEmployee(staff, username, password);
    }

    @Override
    public String toString() {
        return "Staff (" + username + ") " + staff; // or super.toString()?
    }

    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.username);
        hash = 17 * hash + Objects.hashCode(this.password);
        hash = 17 * hash + Objects.hashCode(this.staff);
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
        final StaffEmployee other = (StaffEmployee) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return Objects.equals(this.staff, other.staff);
    }
    
    
}
