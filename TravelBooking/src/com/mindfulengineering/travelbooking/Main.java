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
import com.mindfulengineering.travelbooking.util.OriginSortComparator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

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

        // Collections.sort(tickets);
        // Collections.sort(tickets, new OriginSortComparator());
//        var add2Numbers = (int a, int b) -> { return a + b; };

        BiFunction<TravelTicket, TravelTicket, Integer> departureTimeSort
                = (TravelTicket a, TravelTicket b) -> {
                    return a.getDepartureTime().compareTo(b.getDepartureTime());
                };
        
//        Collections.sort(tickets, new Comparator<TravelTicket>() {
//            @Override
//            public int compare(TravelTicket t, TravelTicket t1) {
//                return t.getDestination().compareTo(t1.getDestination());
//            }
//        });
        Collections.sort(tickets, (a, b)
                -> a.getDepartureTime().compareTo(b.getDepartureTime()));

        for (var b : tickets) {
            System.out.println(b);
        }
        
        tickets.stream().forEach( System.out::println );
        tickets.stream().forEach( a -> System.out.println(a) );
        
        System.out.println("-".repeat(20));
        tickets.stream()
                .filter( a -> a.getOrigin().equals("London") )
                .forEach( System.out::println );
        
        System.out.println("\nJDK17 way of converting toList() ---\n");
        List<TravelTicket> londonTickets = tickets.stream()
                .filter( t -> t.getOrigin().equals("London"))
                .toList();
        
        for (TravelTicket londonTicket : londonTickets) {
            System.out.println(londonTicket);
        }
        
        System.out.println("\nPre JDK17 way of converting toList() ---\n");
        List<TravelTicket> londonTickets2 = tickets.stream()
                .filter( t -> t.getOrigin().equals("London"))
                .collect(Collectors.toList());
        
        tickets.stream().map( a -> {
            a.setPrice(a.getPrice().add(new BigDecimal("20")));
            return a;
        }).forEach(System.out::println);

    }

}
