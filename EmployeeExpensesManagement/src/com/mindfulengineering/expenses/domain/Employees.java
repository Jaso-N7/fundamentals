package com.mindfulengineering.expenses.domain;


/**
 * A wrapper class for holding an array of Employee(s)
 *
 * @author jason
 */
public class Employees {

    private final Employee[] employees;

    /**
     * Constructs a class with an array of employees
     *
     * @param numberOfEmployees Used to set the length of the array
     */
    public Employees(int numberOfEmployees) {
        employees = new Employee[numberOfEmployees];
    }

    /**
     * Adds an employee
     *
     * @param employee
     */
    public void addEmployee(Employee employee) {

        int firstEmptyPosition = -1;

        for (int i = 0; i < employees.length; i++) {
            if (firstEmptyPosition == -1 && employees[i] == null) {
                firstEmptyPosition = i;
            }
        }

        if (firstEmptyPosition == -1) {
            System.out.println("Sorry, the array is full");
        } else {
            employees[firstEmptyPosition] = employee;
        }
    }

    public void viewEmployees() {

        for (Employee e : employees) {
            if (e != null) {
                System.out.println(e.getMailingName());
            }
        }

    }

    /**
     * Finds an employee by their surname
     *
     * @param surname
     * @return An Employee record if found; Otherwise null
     * @throws NullPointerException
     */
    public Employee findBySurname(String surname)
            throws NullPointerException {

        for (Employee e : employees) {
            if (e != null && surname.equalsIgnoreCase(e.getSurname())) {
                return e;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (var e : employees) {
            if (e != null) {
                sb.append(e).append('\n');
            }
        }
        return sb.toString();
    }

}
