/**
 * Used for the Arrays of Objects example
 */
public class Book implements Comparable<Book> {
    
    private String title;
    private String author;
    private Double price;

    public Book(String title, String author, Double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public Book() {}
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return title + " by " + author + " ($" + price + ')';
    }

    @Override
    public int compareTo(Book t) {
        // if this.title should come before o.getTitle() then return -1
        // if this.title should come after o.getTitle() then return +1
        // otherwise return 0
        
        // Naturally sort by title
        // return this.title.compareTo(t.getTitle());
        
        // Sort in reverse by author
        // return - this.author.compareTo(t.getAuthor());
        
        // Sort by author natural order
        return this.author.compareTo(t.getAuthor());
    }
    
    
}
