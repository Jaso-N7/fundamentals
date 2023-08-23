/**
 * Java Fundamentals Unit 10: Arrays of Objects
 * @author jason
 */
public class ArraysOfObjects {
    public static void main(String[] args) {
        
        int[] numbers = {1, 2, 3};
        
        Book[] books = new Book[10];
        
        books[4] = new Book("A good read", "Matt Greencroft", 17.99);
        
        Book myBook; // as this point the reference is null
        myBook = new Book("A good read 2", "Matt Greencroft", 57.99);
        // myBook = null;
        
        if (myBook != null) 
            System.out.println(myBook.getTitle()); // Invokes NullPointerException
        
        books[5] = myBook;
        
        books[8] = new Book("Of mice and men", "John Steinbeck", 16.50);
        
        // Invokes ArrayOutOfBoundsException
//        books[10] = new Book("Pride and prejudice", "Jane Austen", 19.99);
        books[7] = new Book("Pride and prejudice", "Jane Austen", 19.99);
        
        System.out.println("-".repeat(20));
        System.out.println("With C-Style loop");
        
        for (int i = 0; i < books.length; i++) {
            if(books[i] != null)
                System.out.println(books[i].getTitle());
        }
        
        System.out.println("-".repeat(20));
        System.out.println("With enhanced for loop");
        
        for (Book b : books) {
            if (b != null)
                System.out.println(b.getTitle());
        }
    }
}
