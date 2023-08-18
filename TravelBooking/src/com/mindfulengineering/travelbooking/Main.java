package com.mindfulengineering.travelbooking;

import com.mindfulengineering.travelbooking.bookingProcess.BookingSystem;
import com.mindfulengineering.travelbooking.bookingProcess.CheapTravelBookingSystem;
import com.mindfulengineering.travelbooking.bookingProcess.EnjoyableToursBookingSystem;
import com.mindfulengineering.travelbooking.domain.BusTicket;
import com.mindfulengineering.travelbooking.domain.PlaneTicket;
import com.mindfulengineering.travelbooking.domain.TrainTicket;
import com.mindfulengineering.travelbooking.domain.TravelClass;
import com.mindfulengineering.travelbooking.domain.TravelTicket;

import com.mindfulengineering.travelbooking.exceptions.InvalidTravelDurationException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InvalidTravelDurationException {

        TrainTicket trainTicket = new TrainTicket();
        trainTicket.cancel();

        TrainTicket trainTicket2 = null;
        try {
            trainTicket2
                    = new TrainTicket(123L, "London", "Edinburgh",
                            new BigDecimal("59.00"),
                            LocalDateTime.of(2023, 9, 7, 16, 3),
                            LocalDateTime.of(2023, 9, 7, 19, 3), TravelClass.FIRST,
                            3, 42);

            System.out.println(trainTicket2);
            trainTicket2.upgrade();
            trainTicket2.cancel();
        } catch (InvalidTravelDurationException itd) {
            System.out.println("Train Ticket void: " + itd.getMessage());
        }

        ArrayList<String> providers = new ArrayList<>();
        providers.add("JUTC");
        providers.add("Knutsford Express");
        providers.add("JUTA");

        System.out.println("Size of " + providers + ": " + providers.size()); // expects 3 at this point

        providers.remove("JUTA");
        System.out.println("Size of " + providers + ": " + providers.size()); // expects 2 at this point

        providers.remove(1);
        System.out.println("Size of " + providers + ": " + providers.size()); // expects 1 at this point

        BusTicket busTicket = new BusTicket(513L, "Kingston", "Montego Bay",
                new BigDecimal("2500.00"),
                LocalDateTime.of(2023, 3, 7, 16, 3),
                LocalDateTime.of(2023, 3, 7, 19, 3),
                providers);

        BusTicket busTicket2 = new BusTicket(950L, "Kingston", "Mandeville",
                new BigDecimal("2500.00"),
                LocalDateTime.of(2023, 3, 7, 16, 3),
                LocalDateTime.of(2023, 3, 7, 19, 3),
                providers);

        BusTicket busTicket3 = new BusTicket(756L, "Falmouth", "Kingston",
                new BigDecimal("2500.00"),
                LocalDateTime.of(2023, 3, 7, 16, 3),
                LocalDateTime.of(2023, 3, 7, 19, 3),
                providers);

        List<TravelTicket> tickets = new ArrayList<>();
        // HashSet<TravelTicket> tickets = new HashSet<>();
        tickets.add(busTicket);
        tickets.add(busTicket2);
        tickets.add(busTicket2);
        tickets.add(busTicket2);
        tickets.add(busTicket2);
        tickets.add(busTicket2);
        tickets.add(busTicket3);
        tickets.add(trainTicket2);

//        System.out.println(tickets);
//        System.out.println(tickets.get(1)); // Retrieve the second ticket
//
//        for (int i = 0; i < tickets.size(); i++) {
//            System.out.println(tickets.get(i));
//        }
        for (var b : tickets) {
            System.out.println(b);
        }
        
        TravelTicket foundTicket = tickets.get(2);
        System.out.println(foundTicket);

        if (foundTicket instanceof BusTicket bt) {
            
            System.out.println("Found a bus ticket");
            System.out.println(bt.permittedProviders());
            
            bt = new BusTicket(foundTicket, providers);
            
            System.out.println("\nWorking with Interfaces ---\n");
            // With an interface, it doesn't matter which is used, the implementation
            // doesn't change. Uncomment to see the effects
//            BookingSystem bookingSystem = new EnjoyableToursBookingSystem();
            BookingSystem bookingSystem = new CheapTravelBookingSystem();
            
            bookingSystem.setTravelTicket(bt);
            bookingSystem.requestBooking();
            System.out.println("The booking status is " + bookingSystem.getStatus());
            
        } else if (foundTicket instanceof TrainTicket) {
            
            System.out.println("Found a train ticket");
            TrainTicket tt = (TrainTicket) foundTicket;
            System.out.println(tt.getCarriageNumber());
            
        } else {
            System.out.println("This is a plane ticket");
        }
        
        System.out.println("");
        try {

            PlaneTicket planeTicket = new PlaneTicket(1593L, "MKJP", "MIA",
                    new BigDecimal("350.50"),
                    LocalDateTime.of(2023, Month.AUGUST, 31, 10, 05),
                    LocalDateTime.of(2023, Month.AUGUST, 31, 9, 30),
                    TravelClass.BUSINESS, 36, 1);
            planeTicket.upgrade();

        } catch (InvalidTravelDurationException planEx) {
            System.out.println("Plane ticket void: " + planEx.getMessage());
        }
        
        System.out.println("\nUsing Java Interfaces with Lists ---");
        List<Integer> intList1 = new ArrayList<>();
        intList1.add(1);
        intList1.add(2);
        intList1.add(3);
        intList1.add(4);
        intList1.add(5);
        
        // Alternative shortcut way since JDK 11
        List<Integer> intList2 = List.of(1, 2, 3, 4, 5);
        for (Integer integer : intList2) {
            System.out.println(integer);
        }
        System.out.println(intList2.getClass());
        
        intList1.add(6);
        // intList2.add(6); // Immutable, will throw java.lang.UnsupportedOperationException
        
        System.out.println("\nBookingSystem " + BookingSystem.getVersion() + '\n');
        
        BookingSystem bs = BookingSystem.of(foundTicket);
        System.out.println(bs);
    }

}
