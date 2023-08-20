package com.mindfulengineering.travelbooking.domain;

import com.mindfulengineering.travelbooking.exceptions.InvalidTravelDurationException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author jason
 */
public final class TrainTicket extends TravelTicket 
        implements Comparable<TravelTicket> {
    
    private Integer carriageNumber, seatNumber;
    private TravelClass travelClass;

    public TrainTicket() {
        super();
    }
    
    public TrainTicket(Long bookingRef, String origin, String destination, 
            BigDecimal price, LocalDateTime departureTime, LocalDateTime arrivalTime, 
            TravelClass travelClass, Integer carriageNumber, Integer seatNumber) 
            throws InvalidTravelDurationException {
        super(bookingRef, origin, destination, price, departureTime, arrivalTime);
        this.travelClass = travelClass;
        this.carriageNumber = carriageNumber;
        this.seatNumber = seatNumber;
    }

    public TravelClass getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(TravelClass travelClass) {
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
    
        if (travelClass != TravelClass.FIRST) {
            travelClass = TravelClass.FIRST;
            System.out.println("You have been upgraded.");
        } else {
            System.out.println("You are already in " + travelClass + " class");
        }
        
    }

    @Override
    public String toString() {
        return "Train ticket Class " + travelClass + ", in carriage number " + carriageNumber 
                + ", seated at " + seatNumber + '\n' 
                + super.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.travelClass);
        hash = 97 * hash + Objects.hashCode(this.carriageNumber);
        hash = 97 * hash + Objects.hashCode(this.seatNumber);
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
        final TrainTicket other = (TrainTicket) obj;
        if (!Objects.equals(this.travelClass, other.travelClass)) {
            return false;
        }
        if (!Objects.equals(this.carriageNumber, other.carriageNumber)) {
            return false;
        }
        return Objects.equals(this.seatNumber, other.seatNumber);
    }
       
}
