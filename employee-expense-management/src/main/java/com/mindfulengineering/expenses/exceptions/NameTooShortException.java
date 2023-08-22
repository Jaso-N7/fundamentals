package com.mindfulengineering.expenses.exceptions;

/**
 * Thrown to indicate that the employee's name is below the minimum standard.
 * 
 * @author jason
 */
public class NameTooShortException extends Exception {
    
    /**
     * Constructs a NameTooShortException without a detail message.
     */
    public NameTooShortException () { super(); }
}
