package com.mindfulengineering.travelbooking;

import com.mindfulengineering.travelbooking.domain.BusTicket;
import com.mindfulengineering.travelbooking.domain.PlaneTicket;
import com.mindfulengineering.travelbooking.domain.TrainTicket;
import com.mindfulengineering.travelbooking.domain.TravelClass;

import com.mindfulengineering.travelbooking.exceptions.InvalidTravelDurationException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

/**
 *
 * @author jason
 */
public class Main {

    public static void main(String[] args) {

        TrainTicket trainTicket = new TrainTicket();
        trainTicket.cancel();

        try {
            TrainTicket trainTicket2
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

        try {

            String[] providers = {"JUTC", "Knutsford Express", "JUTA"};

            BusTicket busTicket = new BusTicket(513L, "Kingsto", "Montego Bay",
                    new BigDecimal("2500.00"),
                    LocalDateTime.of(2023, 3, 7, 16, 3),
                    LocalDateTime.of(2023, 3, 7, 19, 3),
                    providers);

            busTicket.cancel();
        } catch (InvalidTravelDurationException itd) {
            System.out.println("Bus Ticket void: " + itd.getMessage());
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
