/**
 *
 * @author jason
 */
public class Methods {
    public static void main(String[] args) {
        
        for (String arg : args) {
            System.out.println(arg);
        }
        
        addTwoNumbers(7, 16);
        printMyName();
        int result = addNumbers(7, 16);
        System.out.println("The result was " + result);
       
        System.out.format("The older of 23 or 35 is: %d%n",
                whoIsOlder(23, 35));
        String testString = "Is this sentence longer than 5?";
        System.out.format("%s %s%n",
                testString, longerThan5(testString));
        
        NameManager nameManager = new NameManager();
        
        System.out.println(nameManager.getFirstName());
        System.out.println(nameManager.getSurname());
        
    }
    
    /**
     * Determines tho older of two.
     * 
     * @param firstAge - age of the first
     * @param secondAge - age of the second
     * @return the higher of the two
     */
    public static int whoIsOlder(int firstAge, int secondAge) {
        
        if (firstAge >= secondAge) {
            return firstAge;
        } else {
            return secondAge;
        }
    }
    
    /**
     * Tests the length of a string.
     * 
     * @param str The string to be tested
     * @return True if the string contains 6 or more characters; Otherwise false
     */
    public static boolean longerThan5(String str) {
        return str.length() >= 6;
    }
    
    public static void addTwoNumbers(int n1, int n2) {
        System.out.println(n1 + n2);
    }
    
    public static void printMyName() {
        System.out.println("My name is Jason");
    }
    
    public static int addNumbers(int... nums){
        int result = 0;
        for (int n : nums) {
            result += n;
        }
        
        return result;
    }
    
}
