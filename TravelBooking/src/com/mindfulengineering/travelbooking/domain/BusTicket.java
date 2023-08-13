package com.mindfulengineering.travelbooking.domain;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 *
 * @author jason
 */
public final class BusTicket extends TravelTicket {
    
    private String[] permittedProviders;

    public BusTicket() {
        super();
    }

    public BusTicket(Long bookingRef, String origin, String destination, 
            BigDecimal price, LocalDateTime departureTime, 
            LocalDateTime arrivalTime, String[] permittedProviders) {
        super(bookingRef, origin, destination, price, departureTime, arrivalTime);
        this.permittedProviders = permittedProviders;
    }
    
    public String[] permittedProviders() {
        return permittedProviders;
    }

    public void setPermittedProviders(String[] permittedProviders) {
        this.permittedProviders = permittedProviders;
    }

    @Override
    public void cancel() {
        
        Duration d = Duration.between(LocalDateTime.now(), getDepartureTime());
        Long days = d.toDays();
        if (days > 30) {
            super.cancel();
        } else {
            System.out.println("Sorry, you cannot cancel within 30 days of departure.");
        }
        
    }

    @Override
    public String toString() {
        return "Bus Ticket from " + Arrays.toString(permittedProviders) + '\n' +
                super.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Arrays.deepHashCode(this.permittedProviders);
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
        final BusTicket other = (BusTicket) obj;
        return Arrays.deepEquals(this.permittedProviders, other.permittedProviders);
    }
    
}
