package com.mindfulengineering.tests.expenses;

import com.mindfulengineering.expenses.exceptions.InvalidEmployeeIdException;
import com.mindfulengineering.expenses.utilities.EmployeeUtilities;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SampleTests {

    public SampleTests() {
    }

    @Test
    public void testSomething() {

        int a = 4, b = 8;
        int total = a + b;

        assertEquals(12, total);
    }

    @Test
    public void testEmployeeIdNumberIsConvertedCorrectly()
            throws InvalidEmployeeIdException {
        int result = EmployeeUtilities.validateEmployeeId("416");

        assertEquals(416, result);
    }

    @Test
    public void validateEmployeeIdException() {
        
        assertThrows(
                InvalidEmployeeIdException.class, () -> {
            int result = EmployeeUtilities.validateEmployeeId("hello");
        });
        
//        fail("InvalidEmployeeIdException was not thrown from validateEmployeeId");
    }
}
