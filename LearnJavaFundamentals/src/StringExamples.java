
import javax.swing.JOptionPane;

/**
 *
 * @author jason
 */
public class StringExamples {
    public static void main(String[] args) {
        
        String myName = "Jason";
        int nameLength = myName.length();
        
        JOptionPane.showMessageDialog(null, "My name is " + myName +
                "\nThis has " + nameLength + " characters");
        
        String myNameUpper = myName.toUpperCase();
        JOptionPane.showMessageDialog(null, "My name is " + myNameUpper 
                + " in uppercase");
        
        String phrase = "Many hands make light work.";
        System.out.println( phrase );
        System.out.println(".startsWith(\"Many\"): " + phrase.startsWith("Many"));
        System.out.println(".startsWith(\"many\"): " + phrase.startsWith("many"));
        System.out.println(".indexOf(\"light\"): " + phrase.indexOf("light"));
        System.out.println(".indexOf(\"Light\"): " + phrase.indexOf("Light"));
        System.out.println(".indexOf(\"hello\"): " + phrase.indexOf("hello"));
        System.out.println(".substring(16): " + phrase.substring(16));
        System.out.println(".substring(16, 21): " + phrase.substring(16, 21));
        System.out.println(".replace(\"light\", \"heavy\"): " + 
                phrase.replace("light", "heavy"));
        
        // Testing equality
        myName = "Dale McGovern";
        String yourName = "Dale Smethurst";
        
        String myFirstName = myName.substring(0, 4);
        String yourFirstName = yourName.substring(0, 4);
        
        System.out.println(myFirstName);
        System.out.println(yourFirstName);
        
        // '==' best used with primitives
        // if used with objects, then it checks the reference
//        boolean weHaveTheSameFirstName = myFirstName == yourFirstName;
        boolean weHaveTheSameFirstName = myFirstName.equals(yourFirstName);
        System.out.println(weHaveTheSameFirstName);
        
        // Using ternary operator ?:
        String firstDay = "Sunday",
                lastDay;
        
        if( firstDay.equals("Sunday")) {
            lastDay = "Saturday";
        } else { lastDay = "Sunday"; }
        
        String nextDay = "Sunday".equals(firstDay) ? "Monday" : "Tuesday";
    
        // String concatenation & interpolation templating
        // "Good morning <<surname>>, today is <<nextday>>"
        String greeting = "Good morning " + myFirstName + ", today is " + nextDay;
        String greeting2 = String.format("Good morning %s, today is %s", 
                myFirstName, nextDay);
        System.out.println(greeting);
        System.out.println(greeting2);
        
        String colours = "red, orange, yellow, green, blue, indigo, violet";
        String [] raindbow = colours.split(", ");
        
        for (String hue : raindbow)
            System.out.println(hue);
    }
    
}
