package com.mindfulengineering.travelbooking;

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

/**
 *
 * @author jason
 */
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

        ArrayList<TravelTicket> tickets = new ArrayList<>();
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
            
        } else if (foundTicket instanceof TrainTicket) {
            
            System.out.println("Found a train ticket");
            TrainTicket tt = (TrainTicket) foundTicket;
            System.out.println(tt.getCarriageNumber());
            
        } else {
            System.out.println("This is a plane ticket");
        }
        
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
    }

}
