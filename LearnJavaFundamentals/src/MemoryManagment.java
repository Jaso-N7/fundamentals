public class MemoryManagment {
    
    public static void main(String[] args) {
        
        // An immutable object
        String allNumbers = "";
        
        // Create a number of objects eligible for garbage collection
        for (int i = 1; i < 10; i++) {
            // Strings are immutable, so a new object with new data is created
            // for each iteration
            allNumbers += " " + i;
        }
        
        System.out.println(allNumbers);
        
        System.out.println("\nMore efficient to use StringBuilder ---");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 10; i++) {
            sb.append(" ").append(i);
        }
        System.out.println(sb);
    }
    
}
