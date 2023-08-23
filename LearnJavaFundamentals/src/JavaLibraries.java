
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author jason
 */
public class JavaLibraries {
    
    public static void main(String[] args) {
        Date today = new Date();
        
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(today);
        // Set to yesterdays date
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
        System.out.println(calendar.getTime());
        
        // month is zero-indexed
        GregorianCalendar birthday = new GregorianCalendar(1936, 3, 16);
        Date birthDate = birthday.getTime();
        
        System.out.println(today);                
        System.out.println(birthDate);
        System.out.println(birthday);
              
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String prettyDate = sdf.format(birthDate);
        System.out.println(prettyDate);   
        
        // Using the newer Date(Time) packages
        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime anotherLdt = LocalDateTime.of(2023, 8, 16, 14, 5);
        LocalDate ld = LocalDate.of(2023, 8, 16);
        LocalTime lt = LocalTime.from(anotherLdt);
        
        System.out.println(ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
        System.out.println(lt);
        
        // Activity - Dates and Times
        // LocalDate yesterday = LocalDate.from(ldt.minusDays(1));
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate myBirthday = LocalDate.of(1981, Month.OCTOBER, 3);
        Period howLong = Period.between(myBirthday, LocalDate.from(ldt));
        
        System.out.println("Yesterday: " + yesterday);
        System.out.println("My Birthdate: " + myBirthday);
        System.out.println("How long since birth (" + howLong + ") :" +
                howLong.getYears() + " yrs " + howLong.getMonths() + " mths & " +
                howLong.getDays() + " days");
                
    }
}
