
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exceptions {
    
    private static void getArrayValue () {
        Integer [] ia = new Integer [] {1,2,3,4,5};
        System.out.println(ia[5]);      
    }
    
    private static void dividing (int denominator) {
        System.out.println(17 / denominator);        
    }
    
    private static void calendar () {
        LocalDate today = null;
        System.out.println(today);
        
        if (today != null) // Checking for null
            System.out.println(today.getMonth());
        
    }
    
    private static void numbering () {
        String hello = "hello";
        Double value = Double.valueOf(hello);
    }
    
    private static void createURI(){
        
        try {
            URI sc = new URI("https://www.swisscows.com/en");
            System.out.println("Searching with Swisscows");
            
            URI gg = new URI("-https://www.google.com");
            System.out.println("Searching with Google");
            
        } catch (URISyntaxException ex) {
            throw new IllegalArgumentException("Bad URI");
        } finally {
            System.out.println("finally runs no matter what.");
        }
        
        System.out.println("This is after the try-catch-finally block");
    }
    
    private static void createURIv2 () throws URISyntaxException {
        URI uri = new URI("htttp://www.bing.com");
    }
    
    public static void main(String[] args) throws URISyntaxException {
        
        // getArrayValue();
   
        // dividing(4);
        // dividing(0);

        // calendar();
        // numbering();
            
        try {
            
            User matt = new User("Matt", 21);
            User sally = new User("Sally", -3);
            
        } catch (InvalidAgeException ex) {
            ex.printStackTrace();
        }
        
        
        createURI();
        
        createURIv2();
        
        System.out.println("Code still executing after exception");
    }
    
    
}
