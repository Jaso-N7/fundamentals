package com.mindfulengineering.travelbooking.exceptions;

public class InvalidTravelDurationException extends Exception {
    
    public InvalidTravelDurationException () {}
    
    public InvalidTravelDurationException (String message) {
        super(message);
    }
}
