package com.mindfulengineering.travelbooking;

import com.mindfulengineering.travelbooking.domain.BusTicket;
import com.mindfulengineering.travelbooking.domain.PlaneTicket;
import com.mindfulengineering.travelbooking.domain.TrainTicket;
import com.mindfulengineering.travelbooking.domain.TravelTicket;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author jason
 */
public class Main {
    
    public static void main(String[] args) {
        
        TrainTicket trainTicket = new TrainTicket();
        trainTicket.cancel();
        
        TrainTicket trainTicket2 =
                new TrainTicket(1, 3, 42, 123L, "London", "Edinburgh",
                        new BigDecimal("59.00"), 
                        LocalDateTime.of(2023, 9, 7, 16, 3),
                        LocalDateTime.of(2023, 9, 7, 19, 3));
        
        System.out.println(trainTicket2);
        
        trainTicket2.upgrade();
        
        String[] providers = {"JUTC", "Knutsford Express", "JUTA"};
        
        BusTicket busTicket = new BusTicket(513L, "Kingsto", "Montego Bay", new BigDecimal("2500.00"),
                LocalDateTime.of(2023, 3, 7, 16, 3), 
                LocalDateTime.of(2023, 3, 7, 19, 3), 
                providers);
        
        trainTicket2.cancel();
        busTicket.cancel();
        
        PlaneTicket planeTicket = new PlaneTicket();
        
    }
    
}
