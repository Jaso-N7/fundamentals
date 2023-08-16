package com.mindfulengineering.expenses.exceptions;

/**
 * Thrown to indicate that the application has attempted to convert a string 
 * representing an Employee ID, but that the string does not have the appropriate
 * format.
 * 
 * @author jason
 */
public class InvalidEmployeeIdException extends Exception{
    
    /**
     * Constructs an InvalidEmployeeException with no detail message.
     */
    public InvalidEmployeeIdException () {}
}
