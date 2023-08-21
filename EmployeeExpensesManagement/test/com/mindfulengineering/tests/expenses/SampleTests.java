package com.mindfulengineering.tests.expenses;

import com.mindfulengineering.expenses.exceptions.InvalidEmployeeIdException;
import com.mindfulengineering.expenses.utilities.EmployeeUtilities;
import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author jason
 */
public class SampleTests {

    public SampleTests() {
    }

    @Test
    public void testSomething() {

        int a = 4, b = 8;
        int total = a + b;

        Assert.assertEquals(12, total);
    }

    @Test
    public void testEmployeeIdNumberIsConvertedCorrectly()
            throws InvalidEmployeeIdException {
        int result = EmployeeUtilities.validateEmployeeId("416");

        assertEquals(416, result);
    }

    @Test
    public void validateEmployeeIdException() {
        
        assertThrows("InvalidEmployeeIdException was not thrown from validateEmployeeId", 
                InvalidEmployeeIdException.class, () -> {
            int result = EmployeeUtilities.validateEmployeeId("hello");
        });
        

    }
}
