package com.mindfulengineering.travelbooking.domain;

import com.mindfulengineering.travelbooking.exceptions.InvalidTravelDurationException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author jason
 */
public sealed abstract class TravelTicket 
        implements Comparable<TravelTicket>
        permits PlaneTicket, TrainTicket, BusTicket {
    
    private Long bookingRef;
    private String origin;
    private String destination;
    private BigDecimal price;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public TravelTicket() {
    }

    /**
     * Constructs a TravelTicket instance, which ensures the departure is prior
     * to the arrival
     * 
     * @param bookingRef
     * @param origin
     * @param destination
     * @param price
     * @param departureTime
     * @param arrivalTime
     * @throws InvalidTravelDurationException 
     */
    public TravelTicket(Long bookingRef, String origin, String destination, 
            BigDecimal price, 
            LocalDateTime departureTime, 
            LocalDateTime arrivalTime)
    throws InvalidTravelDurationException {
        
        if (Duration.between(departureTime, arrivalTime).isNegative()) {
            throw new InvalidTravelDurationException("Invalid departure time, cannot arrive before leaving.");
        }
        
        this.bookingRef = bookingRef;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Long getBookingRef() {
        return bookingRef;
    }

    protected void setBookingRef(Long bookingRef) {
        this.bookingRef = bookingRef;
    }

    public String getOrigin() {
        return origin;
    }

    protected void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    protected void setDestination(String destination) {
        this.destination = destination;
    }

    public BigDecimal getPrice() {
        if (price == null)
            return BigDecimal.ZERO;
        
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    protected void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    protected void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void reschedule () {
        System.out.println("I am rescheduling the ticket");
    }
    
    public void cancel () {
        System.out.println("I am cancelling the ticket");
    }

    @Override
    public int compareTo(TravelTicket t) {
        return bookingRef.compareTo(t.getBookingRef());
    }
    
    @Override
    public String toString() {
        return "[# "+ bookingRef + "] from " + origin + " to " + destination 
                + ", $" + price + ", departure " + departureTime 
                + ", arrival " + arrivalTime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.bookingRef);
        hash = 43 * hash + Objects.hashCode(this.origin);
        hash = 43 * hash + Objects.hashCode(this.destination);
        hash = 43 * hash + Objects.hashCode(this.price);
        hash = 43 * hash + Objects.hashCode(this.departureTime);
        hash = 43 * hash + Objects.hashCode(this.arrivalTime);
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
        final TravelTicket other = (TravelTicket) obj;
        if (!Objects.equals(this.origin, other.origin)) {
            return false;
        }
        if (!Objects.equals(this.destination, other.destination)) {
            return false;
        }
        if (!Objects.equals(this.bookingRef, other.bookingRef)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.departureTime, other.departureTime)) {
            return false;
        }
        return Objects.equals(this.arrivalTime, other.arrivalTime);
    }
    
    
}
