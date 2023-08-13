package com.mindfulengineering.travelbooking.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author jason
 */
public final class PlaneTicket extends TravelTicket {
    
    private Integer travelClass, seatNumber, stopOvers;

    public PlaneTicket() {
        super();
    }

    public PlaneTicket(Integer travelClass, Integer seatNumber, Integer stopOvers, 
            Long bookingRef, String origin, String destination, BigDecimal price,
            LocalDateTime departureTime, LocalDateTime arrivalTime) {
        super(bookingRef, origin, destination, price, departureTime, arrivalTime);
        this.travelClass = travelClass;
        this.seatNumber = seatNumber;
        this.stopOvers = stopOvers;
    }

    
    
    public Integer getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(Integer travelClass) {
        this.travelClass = travelClass;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Integer getStopOvers() {
        return stopOvers;
    }

    public void setStopOvers(Integer stopOvers) {
        this.stopOvers = stopOvers;
    }

    public void upgrade () {
        super.setPrice(getPrice().multiply(new BigDecimal("2")));
        System.out.println("You have been upgraded.");
    }
    
    public void addStopOver() {
        stopOvers++;
        System.out.println("You have " + stopOvers + " stopovers.");
    }
    
    @Override
    public String toString() {
        return "Plane Ticket Class=" + travelClass + ", seat # " + seatNumber + 
                ", with " + stopOvers + " stopovers\n" +
                super.toString();
    }
    
    
}
