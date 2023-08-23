/**
 *
 * @author jason
 */
public class Loops {
    
    public static void main(String[] args) {
        
        for (int i = 50; i <= 55; i++) {
            System.out.println(i * 3);
        }
        
        double [] da = {13.5, 12.7, 1.6};
        
        for (int i = 0; i < da.length; i++)
            System.out.println(da[i]);
        
        int counter = 0;
        while (counter < 5) {
            System.out.println("hello " + counter);
            counter++;
        }
        
        int totalScore = 101;
        do {
            System.out.println("Hello from the do...while");
            totalScore++;
        } while (totalScore < 100);
    }
}
