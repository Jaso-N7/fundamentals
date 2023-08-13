package com.mindfulengineering.travelbooking.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author jason
 */
public final class TrainTicket extends TravelTicket {
    
    private Integer travelClass, carriageNumber, seatNumber;

    public TrainTicket() {
        super();
    }
    
    public TrainTicket(Integer travelClass, Integer carriageNumber, 
            Integer seatNumber, Long bookingRef, String origin, 
            String destination, BigDecimal price, LocalDateTime departureTime, 
            LocalDateTime arrivalTime) {
        super(bookingRef, origin, destination, price, departureTime, arrivalTime);
        this.travelClass = travelClass;
        this.carriageNumber = carriageNumber;
        this.seatNumber = seatNumber;
    }

    public Integer getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(Integer travelClass) {
        this.travelClass = travelClass;
    }

    public Integer getCarriageNumber() {
        return carriageNumber;
    }

    public void setCarriageNumber(Integer carriageNumber) {
        this.carriageNumber = carriageNumber;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }
    
    /**
     * Upgrades user seating class to 1st class
     */
    public void upgrade () {
    
        if (travelClass != 1) {
            travelClass = 1;
            System.out.println("You have been upgraded.");
        } else {
            System.out.println("You are already in 1st class");
        }
        
    }

    @Override
    public String toString() {
        return "Train ticket Class " + travelClass + ", in carriage number " + carriageNumber 
                + ", seated at " + seatNumber + '\n' 
                + super.toString();
    }
    
    
    
}
