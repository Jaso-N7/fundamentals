package com.mindfulengineering.expenses;

import com.mindfulengineering.expenses.domain.Department;
import com.mindfulengineering.expenses.domain.Employee;
import java.util.Scanner;

/**
 *
 * @author jason
 */
public class RegisterNewEmployee {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        Employee emp = new Employee();        
     
        System.out.print("Enter the title: ");
        String title = scanner.nextLine();
        emp.setTitle(title);
        
        System.out.print("Enter the first name: ");
        String fn = scanner.nextLine();
        emp.setFirstName(fn);
        
        System.out.print("Enter the surname: ");
        String sn = scanner.nextLine();
        emp.setSurname(sn);
        
        // This is weak, but a good intro
        System.out.print("Enter the department: ");
        String dept = scanner.nextLine();
        // potential area for failure
        Department d = Department.valueOf(dept.toUpperCase());
        emp.setDepartment(d);
        
        System.out.println(emp);
    }
}
