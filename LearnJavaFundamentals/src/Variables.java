/**
 * Java Fundamentals Course - Variables
 * 
 * @author jason
 */
public class Variables {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int i = 1;
        System.out.println(i);

        int j;
        j = 2;
        System.out.println(j);
        
        long myByteLong = 17L;
        long largerThanInt = 3_000_000_000L;
        
        double myDouble = 17.3;
        float myFloat = 17.3F;

        int first = 50,
                second = 100,
                third = first;
        first = 65;
        
        System.out.println("first " + first);
        System.out.println("second " + second);
        System.out.println("third " + third);

        int weExpect2 = second / third;
        System.out.format("%d divided by %d is %d%n",
                second, third, weExpect2);
        
        double weExpectOnePointFive = (double) second / first;
        System.out.format("%d divided by %d is %f%n",
                second, first, weExpectOnePointFive);
        
        int result = 26 / 7;
        System.out.println("the whole part is " + result);
        
        // 26 / 7 = 3 remainder 5
        int remainder = 26 % 7;
        System.out.println("the remainder is " + remainder);
        
        boolean iAmYoungerThan30 = false;
        
        char myFirstNameStartsWith = 'J';
        
        int[] ia = {1,2,3,4,5};
        System.out.println(ia[0]);
        
        ia[3] = 7;
        System.out.println(ia[3]);
    }
    
}
