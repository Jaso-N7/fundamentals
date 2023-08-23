/**
 *
 * @author jason
 */
public class Strings {
    
    public static void main(String[] args) {
        
        String sandra = "Sandra Burnside";
        int spacePosn = sandra.indexOf(" ");
        String sandraSurname = sandra.substring(spacePosn + 1);
        
        boolean evenLength;

        System.out.println("Is the surname (" + sandraSurname + ") an even length?");
        
        if (sandraSurname.length() % 2 == 0) {
            evenLength = true;
            System.out.println("Yes");
        } else { 
            evenLength = false; 
            System.out.println("No");
        }
                
        evenLength = sandraSurname.length() % 2 == 0;
        
        System.out.println(evenLength);
        
    }
}
