package com.mindfulengineering.travelbooking.bookingProcess;

import com.mindfulengineering.travelbooking.domain.TravelTicket;

public interface BookingSystem {
    
    void setTravelTicket( TravelTicket ticket );
    void requestBooking();
    boolean getStatus();
    boolean cancel();
    
    // static methods cannot be Overridden
    // Normally used as a helper to generate the implementation of the interface
    static String getVersion () {
        return "v1.0";
    }
    
    /**
     * Helper method to grant a quick way to creating a BookingSystem from the
     * interface. See example code invocation in main.
     * 
     * @param t
     * @return 
     */
    static BookingSystem of(TravelTicket t) {
        BookingSystem bs = new CheapTravelBookingSystem();
        bs.setTravelTicket(t);
        return bs;
    }
    
    /**
     * Default is NOT a requirement to be Overridden. 
     * Designed to allow backward compatability if we need to make changes in the future
     * without breaking the interface.
     * 
     * @return 
     */
    default boolean isExpired() {
        return true;
    }
}
