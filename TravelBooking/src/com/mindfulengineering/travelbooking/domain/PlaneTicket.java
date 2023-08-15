package com.mindfulengineering.travelbooking.domain;

import com.mindfulengineering.travelbooking.exceptions.InvalidTravelDurationException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author jason
 */
public final class PlaneTicket extends TravelTicket {
    
    private Integer seatNumber, stopOvers;
    private TravelClass travelClass;

    public PlaneTicket() {
        super();
    }

    public PlaneTicket(Long bookingRef, String origin, String destination, 
            BigDecimal price, LocalDateTime departureTime, LocalDateTime arrivalTime,
            TravelClass travelClass, Integer seatNumber, Integer stopOvers)
    throws InvalidTravelDurationException {
        super(bookingRef, origin, destination, price, departureTime, arrivalTime);
        this.travelClass = travelClass;
        this.seatNumber = seatNumber;
        this.stopOvers = stopOvers;
    }

    
    
    public TravelClass getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(TravelClass travelClass) {
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
        return "Plane Ticket " + travelClass + " Class, seat # " + seatNumber + 
                ", with " + stopOvers + " stopovers\n" +
                super.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.travelClass);
        hash = 97 * hash + Objects.hashCode(this.seatNumber);
        hash = 97 * hash + Objects.hashCode(this.stopOvers);
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
        final PlaneTicket other = (PlaneTicket) obj;
        if (!Objects.equals(this.travelClass, other.travelClass)) {
            return false;
        }
        if (!Objects.equals(this.seatNumber, other.seatNumber)) {
            return false;
        }
        return Objects.equals(this.stopOvers, other.stopOvers);
    }
    
    
}
