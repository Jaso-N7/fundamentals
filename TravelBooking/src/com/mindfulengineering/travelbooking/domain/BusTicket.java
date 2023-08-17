package com.mindfulengineering.travelbooking.domain;

import com.mindfulengineering.travelbooking.exceptions.InvalidTravelDurationException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author jason
 */
public final class BusTicket extends TravelTicket {
    
    private ArrayList<String> permittedProviders;

    public BusTicket() {
        super();
    }

    public BusTicket(Long bookingRef, String origin, String destination, 
            BigDecimal price, LocalDateTime departureTime, 
            LocalDateTime arrivalTime, ArrayList<String> permittedProviders)
    throws InvalidTravelDurationException {
        super(bookingRef, origin, destination, price, departureTime, arrivalTime);
        this.permittedProviders = permittedProviders;
    }
    
    public ArrayList<String> permittedProviders() {
        return permittedProviders;
    }

    public void setPermittedProviders(ArrayList<String> permittedProviders) {
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
        return permittedProviders + " Bus Ticket " + super.toString() + '\n';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.permittedProviders);
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
        return Objects.equals(this.permittedProviders, other.permittedProviders);
    }

}
