
import java.math.BigDecimal;

/**
 * Working with precision decimals
 * 
 * @author jason
 */
public class Money {
    public static void main(String[] args) {
        
        // Starting with $1, save 0.10c for 10 days
        
        // Imprecise 
        System.out.println("Imprecise ---");
        Double d = 1d;
        for (int i = 0; i < 10; i++) {
            d += 0.1;
            System.out.println(d);
        }
        
        System.out.println("Precise ---");
        // Precise
        BigDecimal total = new BigDecimal("1");
        for (int i = 0; i < 10; i++) {
            total = total.add( new BigDecimal("0.1") );
            System.out.println(total);
        }
          
        BigDecimal one = BigDecimal.ONE; // = new BigDecimal("1");
        one = null;
        
    }
 
}
