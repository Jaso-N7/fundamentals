import javax.swing.*;

/**
 *
 * @author jason
 */
public class Conditions {
    
    public static void main(String[] args) {
        
        String msg = "";
        int testScore = 100;
        
        switch (testScore) {
            case 0:
                msg = """
                    Did you even take the test?
                    You scored zero
                    """;
                System.out.println(msg);
                
                JOptionPane.showMessageDialog(null, msg);
                break;
            case 100:
                msg = "You got top marks!";
                System.out.println( msg );
                JOptionPane.showMessageDialog(null, msg);
                // break;
            default:
                msg = "You didn't do anything special";
                System.out.println(msg);
                JOptionPane.showMessageDialog(null, msg);
                break;
        }
        
        // if score > 50 and score is divisible by 10
        if (testScore > 50 && testScore % 10 == 0) {
            msg = "You passed with a score ending in a zero";
            System.out.println(msg);
            JOptionPane.showMessageDialog(null, msg);
        }
    }
}
