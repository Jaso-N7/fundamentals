package com.mindfulengineering.travelbooking;

import com.mindfulengineering.travelbooking.domain.*;
import com.mindfulengineering.travelbooking.exceptions.InvalidTravelDurationException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

/**
 *
 * @author jason
 */
public class RegisterTravelTicket {

    private final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) 
            throws InvalidTravelDurationException {

        System.out.print("Booking for 1 - bus, 2 - train, 3 - plane? ");
        int answer = sc.nextInt();

        // optionally while ( !(answer >= 1 && answer <=3) )
        if (answer >= 1 && answer <= 3) {
            var ticket = bookTicket(answer);
            System.out.println(ticket);
        } else {
            System.out.println("Invalid input, goodbye");
        }

    }

    private static TravelTicket bookTicket(int choice) 
            throws InvalidTravelDurationException {

        System.out.println("Booking Ref");
        long br = sc.nextLong();
        sc.nextLine();

        System.out.println("Leaving from");
        String from = sc.nextLine();

        System.out.println("Date of Departure (yyyy-mm-dd)");
        String depDate = sc.nextLine();
        LocalDate ldDepart = LocalDate.parse(depDate);

        System.out.println("Time of Departure (hh:mm)");
        String depTime = sc.nextLine();
        LocalTime ltDepart = LocalTime.parse(depTime);

        System.out.println("Destination");
        String to = sc.nextLine();

        System.out.println("Date of Arrival (yyyy-mm-dd)");
        String arrDate = sc.nextLine();
        LocalDate ldArrival = LocalDate.parse(arrDate);

        System.out.println("Time of Arrival (hh:mm)");
        String arrTime = sc.nextLine();
        LocalTime ltArrival = LocalTime.parse(arrTime);

        System.out.println("Cost of ticket");
        BigDecimal cost = sc.nextBigDecimal();
        sc.nextLine();

        LocalDateTime ldtDepart = LocalDateTime.of(ldDepart, ltDepart);
        LocalDateTime ldtArrival = LocalDateTime.of(ldArrival, ltArrival);

        return switch (choice) {
            case 1 -> {
                yield new BusTicket(br, from, to, cost, ldtDepart, ldtArrival, null);
            }
            case 2 -> {

                System.out.println("Seat Class");
                String seat = sc.nextLine();
                TravelClass tc = TravelClass.valueOf(seat.toUpperCase());

                System.out.println("Carriage No.");
                int carNo = sc.nextInt();
                sc.nextLine();

                System.out.println("Seat No.");
                int seatNo = sc.nextInt();

                yield new TrainTicket(br, from, to, cost, ldtDepart, ldtArrival, tc, carNo, seatNo);
            }
            default -> {

                System.out.println("Seat Class");
                String seat = sc.nextLine();
                TravelClass tc = TravelClass.valueOf(seat.toUpperCase());

                System.out.println("Seat No.");
                int seatNo = sc.nextInt();
                sc.nextLine();

                System.out.println("How many stopovers?");
                int stops = sc.nextInt();

                yield new PlaneTicket(br, from, to, cost, ldtDepart, ldtArrival, tc, seatNo, stops);
            }
        };
    }

}
