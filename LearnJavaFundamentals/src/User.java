
import java.util.Objects;

class InvalidAgeException extends Exception {

    public InvalidAgeException() {
    }

    public InvalidAgeException(String message) {
        super(message);
    }

    
}

/**
 * Practising exception handling
 * 
 */
public class User {
    
    private String name;
    private int age;

    public User(String name, int age) throws InvalidAgeException {
        
        if( age < 0 ) {
            throw new InvalidAgeException("Age cannot be negative!");
        }
        if (age > 200) {
            throw new InvalidAgeException("Age cannot be greater than 200!");
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + " is " + age + " year(s) old";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + this.age;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.age != other.age) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }
    
    
    
}
