package com.mindfulengineering.travelbooking.bookingProcess;

import com.mindfulengineering.travelbooking.domain.TravelTicket;

public class CheapTravelBookingSystem implements BookingSystem {

    @Override
    public void setTravelTicket(TravelTicket ticket) {
        System.out.println("Cheap Travel has recieved ticket \n" + ticket);
    }

    @Override
    public void requestBooking() {
        System.out.println("Cheap Travel has recieved a booking request");

    }

    @Override
    public boolean getStatus() {
        System.out.println("Cheap travel sent the booking status");
        return false;
    }

    @Override
    public boolean cancel() {
        return false;
    }
    
}
