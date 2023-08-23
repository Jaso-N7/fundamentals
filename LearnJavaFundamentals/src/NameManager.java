/**
 * Activity - Class Level Variables
 * @author jason
 */
public class NameManager {
    
    String fullName = "Jason Robinson";
    
    public String getFirstName () {
        // alternative: return fullName.substring(0, fullName.indexOf(" "));
        return fullName.split(" ")[0];
    }
    
    public String getSurname() {
        // String [] arr = fullName.split(" ");
        // return arr[1];
        return fullName.split(" ")[1]; // short form of the above
    }
}
