
import java.util.*;

public class ManipulatingCollections {

    public static void main(String[] args) {

        List<String> firstList = new ArrayList<>(List.of("one", "two", "three", "four", "five"));

        for (String s : firstList) {
            System.out.println(s);
        }

        // sort by alphabetical order
        Collections.sort(firstList);

        for (String s : firstList) {
            System.out.println(s);
        }

        List<Book> books = new LinkedList<>();
        books.add(new Book("First Book", "Matt Greencroft", 15.99));
        books.add(new Book("A Good Read", "Sally Mauve", 29.99));
        books.add(new Book("Something Quick to Read", "Dave Green", 29.99));

        // How do we sort on books? What is the natural sort order?
        Collections.sort(books);
        for (Book book : books) {
            System.out.println(book);
        }

        // Only objects for K,V. Primitives won't work
        Map<Integer, Book> bookMap = new HashMap<>();
        bookMap.put(17, books.get(0));
        bookMap.put(3, books.get(1));
        bookMap.put(21, books.get(2));
        // this will override the previous value for the specified key
        bookMap.put(3, new Book("More reading", "Michael Black", 29.19));

        System.out.println("bookMap.size() => " + bookMap.size());
        Book foundBook = bookMap.get(3);
        System.out.println(foundBook);

        System.out.println("""
                           \nRead through the objects in the Map
                           in the order of the keys ---
                           """);
        Set<Integer> keys = bookMap.keySet();
        for (Integer key : keys) {
            System.out.println(key + ": " + bookMap.get(key));
        }

        System.out.println("\nLoop through the values of a Map ---\n");

        var values = bookMap.values();
        for (Book b : values) {
            System.out.println(b);
        }

        System.out.println("\nSorting the values ---\n");

        List<Book> bookList = new LinkedList<>(bookMap.values());
        Collections.sort(bookList);
        System.out.println(bookList);

        System.out.println("\nRemoving values '.remove(3)' ---\n");
        bookMap.remove(3);
        System.out.println(bookMap);

        System.out.println("\nJDK9+ Creating immutable Map<Integer, String> with .of() ---\n");

        Map<Integer, String> myMap
                = Map.of(1, "one", 2, "two", 3, "three");
        System.out.println(myMap);
    }
}
